package co.edu.unal.reto8.dataAccess.db;

import androidx.room.RoomDatabase;
import co.edu.unal.reto8.dataAccess.model.Contact;
import co.edu.unal.reto8.dataAccess.dao.ContactDao;

@androidx.room.Database(entities = {Contact.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract ContactDao contactDao();
}
