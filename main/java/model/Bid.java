package byhiras.main.java.model;

import java.math.BigDecimal;

public class Bid
{
    private Long bidId;
    private Long itemId;
    private String user;
    private BigDecimal bidAmount;
    private boolean winningBid;

    public Long getBidId()
    {
        return bidId;
    }

    public void setBidId( Long bidId )
    {
        this.bidId = bidId;
    }

    public Long getItemId()
    {
        return itemId;
    }

    public void setItemId( Long itemId )
    {
        this.itemId = itemId;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser( String user )
    {
        this.user = user;
    }

    public BigDecimal getBidAmount()
    {
        return bidAmount;
    }

    public void setBidAmount( BigDecimal bidAmount )
    {
        this.bidAmount = bidAmount;
    }

    public boolean isWinningBid()
    {
        return winningBid;
    }

    public void setWinningBid( boolean winningBid )
    {
        this.winningBid = winningBid;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( !( o instanceof Bid ) ) return false;

        Bid bid = (Bid) o;

        if ( winningBid != bid.winningBid ) return false;
        if ( !bidAmount.equals( bid.bidAmount ) ) return false;
        if ( !bidId.equals( bid.bidId ) ) return false;
        if ( !itemId.equals( bid.itemId ) ) return false;
        if ( !user.equals( bid.user ) ) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = bidId.hashCode();
        result = 31 * result + itemId.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + bidAmount.hashCode();
        result = 31 * result + ( winningBid ? 1 : 0 );
        return result;
    }
}
