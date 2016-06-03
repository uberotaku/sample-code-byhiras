package byhiras.main.java.service;

import byhiras.main.java.data.ItemDao;
import byhiras.main.java.model.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemService
{
    @Autowired
    ItemDao itemDao;

    public List<Item> fetchAllItemsForUser( String user ) throws ServiceException
    {
        try
        {
            return itemDao.fetchAllItemsForUser( user );
        }
        catch ( Exception e )
        {
            throw new ServiceException( "Error fetching all items" );
        }
    }
}
