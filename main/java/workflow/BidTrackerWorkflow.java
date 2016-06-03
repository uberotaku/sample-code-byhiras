package byhiras.main.java.workflow;

import byhiras.main.java.model.Bid;
import byhiras.main.java.model.Item;
import byhiras.main.java.service.BidService;
import byhiras.main.java.service.ItemService;
import byhiras.main.java.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BidTrackerWorkflow
{
    @Autowired
    ItemService itemService;

    @Autowired
    BidService bidService;

    public void recordBid( Bid bid ) throws ServiceException
    {
        if ( bid == null )
            throw new IllegalArgumentException( "Bid cannot be null" );

        List<Bid> existingBids = fetchAllBidsForItem( bid.getItemId() );
        bid.setWinningBid( existingBids.isEmpty() ? true : false );
        for ( Bid existingBid : existingBids )
        {
            if ( existingBid.isWinningBid() && bid.getBidAmount().compareTo( existingBid.getBidAmount() ) > 0 )
            {
                bidService.updateWinningBid( existingBid.getBidId() );
                bid.setWinningBid( true );
                break;
            }
        }

        bidService.recordBid( bid );
    }

    public Bid getWinningBid( Long itemId ) throws ServiceException
    {
        if ( itemId == null )
            throw new IllegalArgumentException( "Item Id cannot be null" );

        return bidService.getWinningBid( itemId );
    }

    public List<Bid> fetchAllBidsForItem( Long itemId ) throws ServiceException
    {
        if ( itemId == null )
            throw new IllegalArgumentException( "Item Id cannot be null" );

        return bidService.fetchAllBidsForItem( itemId );
    }

    public List<Item> fetchAllItemsForUser( String user ) throws ServiceException
    {
        if ( user == null )
            throw new IllegalArgumentException( "User cannot be null" );

        return itemService.fetchAllItemsForUser( user );
    }
}
