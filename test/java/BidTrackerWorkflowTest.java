package byhiras.test.java;

import byhiras.main.java.model.Bid;
import byhiras.main.java.service.BidService;
import byhiras.main.java.service.ItemService;
import byhiras.main.java.workflow.BidTrackerWorkflow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith( MockitoJUnitRunner.class )
public class BidTrackerWorkflowTest
{
    @Mock
    BidService bidService;

    @Mock
    ItemService itemService;

	@InjectMocks
    BidTrackerWorkflow workflow;

    private Bid bid;
    private List<Bid> existingBids;

    @Before
    public void setup()
    {
        bid = new Bid();
        bid.setItemId( 1L );
        bid.setUser( "user1" );
        bid.setBidAmount( BigDecimal.ONE );
        bid.setWinningBid( false );

        Bid exBid1 = new Bid();
        exBid1.setBidId( 1L );
        exBid1.setItemId( 1L );
        exBid1.setUser( "user1" );
        exBid1.setBidAmount( new BigDecimal( "100.00" ) );
        exBid1.setWinningBid( false );

        Bid exBid2 = new Bid();
        exBid2.setBidId( 2L );
        exBid2.setItemId( 1L );
        exBid2.setUser( "user1" );
        exBid2.setBidAmount( new BigDecimal( "500.00" ) );
        exBid2.setWinningBid( true );

        Bid exBid3 = new Bid();
        exBid3.setBidId( 3L );
        exBid3.setItemId( 1L );
        exBid3.setUser( "user1" );
        exBid3.setBidAmount( new BigDecimal( "75.00" ) );
        exBid3.setWinningBid( false );

        existingBids = new ArrayList();
        existingBids.add( exBid1 );
        existingBids.add( exBid2 );
        existingBids.add( exBid3 );
    }

	@Test
	public void testRecordBid_NoExistingBids()
	{
        when( bidService.fetchAllBidsForItem( anyLong() ) ).thenReturn( Collections.EMPTY_LIST );

        workflow.recordBid( bid );
        verify( bidService ).recordBid( bid );

        assertTrue( bid.isWinningBid() );
	}

    @Test
    public void testRecordBid_ExistingBids_NotWinningBid()
    {
        when( bidService.fetchAllBidsForItem( anyLong() ) ).thenReturn( existingBids );

        workflow.recordBid( bid );
        verify( bidService, times(0) ).updateWinningBid( anyLong() );
        verify( bidService ).recordBid( bid );

        assertFalse( bid.isWinningBid() );
    }

    @Test
    public void testRecordBid_ExistingBids_IsWinningBid()
    {
        bid.setBidAmount( new BigDecimal( "700.00" ) );

        when( bidService.fetchAllBidsForItem( anyLong() ) ).thenReturn( existingBids );

        workflow.recordBid( bid );
        verify( bidService, times(1) ).updateWinningBid( 2L );
        verify( bidService ).recordBid( bid );

        assertTrue( bid.isWinningBid() );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testRecordBid_Null()
    {
        workflow.recordBid( null );
    }

    @Test
    public void testGetWinningBid()
    {
        when( bidService.getWinningBid( anyLong() ) ).thenReturn( new Bid() );

        workflow.getWinningBid( 1L );
        verify( bidService ).getWinningBid( 1L );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testGetWinningBid_Null()
    {
        workflow.getWinningBid( null );
    }

    @Test
    public void testFetchAllBidsForItem()
    {
        when( bidService.fetchAllBidsForItem( anyLong() ) ).thenReturn( Collections.EMPTY_LIST );

        workflow.fetchAllBidsForItem( 1L );
        verify( bidService ).fetchAllBidsForItem( 1L );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFetchAllBidsForItem_Null()
    {
        workflow.fetchAllBidsForItem( null );
    }

    @Test
    public void testFetchAllBidsForUser()
    {
        when( itemService.fetchAllItemsForUser( anyString() ) ).thenReturn( Collections.EMPTY_LIST );

        workflow.fetchAllItemsForUser( "user1" );
        verify( itemService ).fetchAllItemsForUser( "user1" );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testFetchAllBidsForUser_Null()
    {
        workflow.fetchAllBidsForItem( null );
    }
}
