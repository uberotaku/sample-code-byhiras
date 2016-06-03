package byhiras.test.java;

import byhiras.main.java.model.Bid;
import byhiras.main.java.resource.BidTrackerResource;
import byhiras.main.java.workflow.BidTrackerWorkflow;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith( MockitoJUnitRunner.class )
public class BidTrackerResourceTest
{
	@Mock
    BidTrackerWorkflow workflow;

    @InjectMocks
    private BidTrackerResource resource;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testRecordBid() throws Exception
    {
        Bid bid = new Bid();
        resource.recordBid( bid );
        verify( workflow ).recordBid( bid );
	}

    @Test
    public void testRecordBid_e() throws Exception
    {
        Bid bid = new Bid();

        doThrow( new RuntimeException() ).when( workflow ).recordBid( any( Bid.class ) );
        thrown.expect( Exception.class );
        thrown.expectMessage( "Internal Server Error - Contact Support" );

        resource.recordBid( bid );
    }

    @Test
    public void testGetWinningBid() throws Exception
    {
        resource.getWinningBid( 1L );
        verify( workflow ).getWinningBid( 1L );
    }

    @Test
    public void testGetWinningBid_e() throws Exception
    {
        when( workflow.getWinningBid( anyLong() ) ).thenThrow( new RuntimeException() );
        thrown.expect( Exception.class );
        thrown.expectMessage( "Internal Server Error - Contact Support" );

        resource.getWinningBid( 1L );
    }

    @Test
    public void testFetchAllBidsForItem() throws Exception
    {
        resource.fetchAllBidsForItem( 1L );
        verify( workflow ).fetchAllBidsForItem( 1L );
    }

    @Test
    public void testFetchAllBidsForItem_e() throws Exception
    {
        when( workflow.fetchAllBidsForItem( anyLong() ) ).thenThrow( new RuntimeException() );
        thrown.expect( Exception.class );
        thrown.expectMessage( "Internal Server Error - Contact Support" );

        resource.fetchAllBidsForItem( 1L );
    }

    @Test
    public void testFetchAllItemsForUser() throws Exception
    {
        resource.fetchAllItemsForUser( "user1" );
        verify( workflow ).fetchAllItemsForUser( "user1" );
    }

    @Test
    public void testFetchAllItemsForUser_e() throws Exception
    {
        when( workflow.fetchAllItemsForUser( anyString() ) ).thenThrow( new RuntimeException() );
        thrown.expect( Exception.class );
        thrown.expectMessage( "Internal Server Error - Contact Support" );

        resource.fetchAllItemsForUser( "user1" );
    }
}
