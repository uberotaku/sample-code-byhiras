package byhiras.main.java.data;

import byhiras.main.java.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bidDao")
public class BidDaoImpl implements BidDao
{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterTemplate;

    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Bid> fetchAllBidsForItem( Long itemId ) throws DataAccessException
    {
        String sql = "select * from bid where item_id = :ITEM";

        Map<String, Object> params = new HashMap();
        params.put( "ITEM", itemId );

        return namedParameterTemplate.queryForList( sql, params, Bid.class );
    }

    @Override
    public Bid fetchWinningBid( Long itemId ) throws DataAccessException
    {
        String sql = "select * from bid where item_id = :ITEM and winning_bid = 1";

        Map<String, Object> params = new HashMap();
        params.put( "ITEM", itemId );

        return namedParameterTemplate.queryForObject( sql, params, Bid.class );
    }

    @Override
    public void recordBid( Bid bid ) throws DataAccessException
    {
        String sql = "insert into bid (item_id, user, bid_amount, winning_bid) values(?,?,?,?) ";

        template.update( sql, bid.getItemId(), bid.getUser(), bid.getBidAmount(), bid.isWinningBid() );
    }

    @Override
    public void updateWinningBid( Long bidId ) throws DataAccessException
    {
        String sql = "update bid set winning_bid = 0 where bid_id = ?";

        template.update( sql, bidId );
    }
}
