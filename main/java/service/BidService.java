package byhiras.main.java.service;

import byhiras.main.java.data.BidDao;
import byhiras.main.java.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BidService
{
    @Autowired
    BidDao bidDao;

    public void recordBid( Bid bid ) throws ServiceException
    {
        try
        {
            bidDao.recordBid( bid );
        }
        catch ( Exception e )
        {
            throw new ServiceException( "Error recording bid" );
        }
    }

    public void updateWinningBid( Long bidId ) throws ServiceException
    {
        try
        {
            bidDao.updateWinningBid( bidId );
        }
        catch ( Exception e )
        {
            throw new ServiceException( "Error updating winning bid" );
        }
    }

    public Bid getWinningBid( Long itemId ) throws ServiceException
    {
        try
        {
            return bidDao.fetchWinningBid( itemId );
        }
        catch ( Exception e )
        {
            throw new ServiceException( "Error fetching winning bid" );
        }
    }

    public List<Bid> fetchAllBidsForItem( Long itemId ) throws ServiceException
    {
        try
        {
            return bidDao.fetchAllBidsForItem( itemId );
        }
        catch ( Exception e )
        {
            throw new ServiceException( "Error fetching all items" );
        }
    }
}
