package byhiras.main.java.data;

import byhiras.main.java.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemDao")
public class ItemDaoImpl implements ItemDao
{
    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Item> fetchAllItemsForUser( String user ) throws DataAccessException
    {
        String sql = "select * from item where user = ?";

        return template.queryForList( sql, Item.class, user );
    }
}
