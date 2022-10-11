package co.edu.unal.reto8.dataAccess.repository;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import co.edu.unal.reto8.dataAccess.db.Database;
import co.edu.unal.reto8.dataAccess.model.Contact;

public class ContactRepository {
    private String DB_NAME = "reto8db";
    private Database database;

    public ContactRepository(Context context){
        database = Room.databaseBuilder(context, Database.class, DB_NAME).
                allowMainThreadQueries().build();
    }

    public List<Contact> getAllContacts() {
        return database.contactDao().getAllContacts();
    }

    public Contact getContactById(int nit) {
        return database.contactDao().getContactById(nit);
    }

    public void createContact(final Contact contact) {
        database.contactDao().createContact(contact);
    }

    public void updateContact(Contact contact) {
        database.contactDao().updateContact(contact);
    }

    public String getDB_NAME() {
        return DB_NAME;
    }

    public Database getDatabase() {
        return database;
    }

    public void deleteContact(int nit) {
        Contact contact = database.contactDao().getContactById(nit);
        database.contactDao().deleteContact(contact);
    }
}
