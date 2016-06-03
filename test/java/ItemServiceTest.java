package byhiras.test.java;

import byhiras.main.java.data.BidDao;
import byhiras.main.java.data.ItemDao;
import byhiras.main.java.model.Bid;
import byhiras.main.java.service.BidService;
import byhiras.main.java.service.ItemService;
import byhiras.main.java.service.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith( MockitoJUnitRunner.class )
public class ItemServiceTest
{
    @Mock
    ItemDao itemDao;

	@InjectMocks
    ItemService service;


    @Test
    public void testFetchAllItemsForUser()
    {
        service.fetchAllItemsForUser( "user1" );
        verify( itemDao ).fetchAllItemsForUser(  "user1" );
    }

    @Test( expected = ServiceException.class )
    public void testFetchAllItemsForUser_e()
    {
        when( itemDao.fetchAllItemsForUser( anyString() ) ).thenThrow( new RuntimeException() );
        service.fetchAllItemsForUser(  "user1" );
    }
}
