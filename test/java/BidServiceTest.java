package byhiras.test.java;

import byhiras.main.java.data.BidDao;
import byhiras.main.java.model.Bid;
import byhiras.main.java.service.BidService;
import byhiras.main.java.service.ItemService;
import byhiras.main.java.service.ServiceException;
import byhiras.main.java.workflow.BidTrackerWorkflow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith( MockitoJUnitRunner.class )
public class BidServiceTest
{
    @Mock
    BidDao bidDao;

	@InjectMocks
    BidService service;

    private Bid bid;

    @Before
    public void setup()
    {
        bid = new Bid();
    }

	@Test
	public void testRecordBid()
	{
        service.recordBid( bid );
        verify( bidDao ).recordBid( bid );
	}

    @Test( expected = ServiceException.class )
    public void testRecordBid_e()
    {
        doThrow( new RuntimeException() ).when( bidDao ).recordBid( any( Bid.class ) );
        service.recordBid( bid );
    }

    @Test
    public void testUpdateWinningBid()
    {
        service.updateWinningBid( 1L );
        verify( bidDao ).updateWinningBid( 1L );
    }

    @Test( expected = ServiceException.class )
    public void testUpdateWinningBid_e()
    {
        doThrow( new RuntimeException() ).when( bidDao ).updateWinningBid( anyLong() );
        service.updateWinningBid( 1L );
    }

    @Test
    public void testGetWinningBid()
    {
        service.getWinningBid( 1L );
        verify( bidDao ).fetchWinningBid( 1L );
    }

    @Test( expected = ServiceException.class )
    public void testGetWinningBid_e()
    {
        when( bidDao.fetchWinningBid( anyLong() ) ).thenThrow( new RuntimeException() );
        service.getWinningBid( 1L );
    }

    @Test
    public void testFetchAllBidsForItem()
    {
        service.fetchAllBidsForItem( 1L );
        verify( bidDao ).fetchAllBidsForItem( 1L );
    }

    @Test( expected = ServiceException.class )
    public void testFetchAllBidsForItem_e()
    {
        when( bidDao.fetchAllBidsForItem( anyLong() ) ).thenThrow( new RuntimeException() );
        service.fetchAllBidsForItem( 1L );
    }
}
