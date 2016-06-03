package byhiras.main.java.data;

import byhiras.main.java.model.Bid;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BidDao
{
    List<Bid> fetchAllBidsForItem( Long itemId ) throws DataAccessException;

    Bid fetchWinningBid( Long itemId ) throws DataAccessException;

    void recordBid( Bid bid ) throws DataAccessException;

    void updateWinningBid( Long bidId ) throws DataAccessException;
}
