package byhiras.main.java.resource;

import byhiras.main.java.model.Bid;
import byhiras.main.java.model.Item;
import byhiras.main.java.workflow.BidTrackerWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

public class BidTrackerResource
{
    private static final Logger log = LoggerFactory.getLogger( BidTrackerResource.class );

    @Autowired
    BidTrackerWorkflow bidTrackerWorkflow;

    @GET
    @Path( "/bid/" )
    public void recordBid( @QueryParam( "bid" ) Bid bid ) throws Exception
    {
        try
        {
            bidTrackerWorkflow.recordBid( bid );
        }
        catch ( RuntimeException re )
        {
            log.error( "An exception occurred recordBid", re );
            throw new Exception( "Internal Server Error - Contact Support" );
        }
    }

    @GET
    @Path( "/winner/" )
    public Bid getWinningBid( @QueryParam( "item" ) Long itemId ) throws Exception
    {
        try
        {
            return bidTrackerWorkflow.getWinningBid( itemId );
        }
        catch ( RuntimeException re )
        {
            log.error( "An exception occurred getWinningBid", re );
            throw new Exception( "Internal Server Error - Contact Support" );
        }
    }

    @GET
    @Path( "/bids/" )
    public List<Bid> fetchAllBidsForItem( @QueryParam( "item" ) Long itemId ) throws Exception
    {
        try
        {
            return bidTrackerWorkflow.fetchAllBidsForItem( itemId );
        }
        catch ( RuntimeException re )
        {
            log.error( "An exception occurred fetchAllBidsForItem", re );
            throw new Exception( "Internal Server Error - Contact Support" );
        }
    }

    @GET
    @Path( "/items/" )
    public List<Item> fetchAllItemsForUser( @QueryParam( "user" ) String user ) throws Exception
    {
        try
        {
            return bidTrackerWorkflow.fetchAllItemsForUser( user );
        }
        catch ( RuntimeException re )
        {
            log.error( "An exception occurred fetchAllItemsForUser", re );
            throw new Exception( "Internal Server Error - Contact Support" );
        }
    }
}
