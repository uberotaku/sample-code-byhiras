package byhiras.main.java.model;

public class Item
{
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private ItemStatus itemStatus;

    public Long getItemId()
    {
        return itemId;
    }

    public void setItemId( Long itemId )
    {
        this.itemId = itemId;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName( String itemName )
    {
        this.itemName = itemName;
    }

    public String getItemDescription()
    {
        return itemDescription;
    }

    public void setItemDescription( String itemDescription )
    {
        this.itemDescription = itemDescription;
    }

    public ItemStatus getItemStatus()
    {
        return itemStatus;
    }

    public void setItemStatus( ItemStatus itemStatus )
    {
        this.itemStatus = itemStatus;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( !( o instanceof Item ) ) return false;

        Item item = (Item) o;

        if ( itemDescription != null ? !itemDescription.equals( item.itemDescription ) : item.itemDescription != null )
            return false;
        if ( !itemId.equals( item.itemId ) ) return false;
        if ( !itemName.equals( item.itemName ) ) return false;
        if ( itemStatus != item.itemStatus ) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = itemId.hashCode();
        result = 31 * result + itemName.hashCode();
        result = 31 * result + ( itemDescription != null ? itemDescription.hashCode() : 0 );
        result = 31 * result + itemStatus.hashCode();
        return result;
    }
}
