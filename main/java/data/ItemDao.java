package byhiras.main.java.data;

import byhiras.main.java.model.Item;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ItemDao
{
    List<Item> fetchAllItemsForUser( String user ) throws DataAccessException;
}
