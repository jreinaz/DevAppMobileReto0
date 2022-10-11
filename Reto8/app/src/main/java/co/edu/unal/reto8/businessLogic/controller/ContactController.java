package co.edu.unal.reto8.businessLogic.controller;

import android.content.Context;
import android.util.Log;

import java.util.List;

import co.edu.unal.reto8.dataAccess.model.Contact;
import co.edu.unal.reto8.dataAccess.repository.ContactRepository;

public class ContactController {
    private ContactRepository contactRepository;

    public ContactController(){

    }

    public void createContact(Contact contact, Context context) {
        contactRepository = new ContactRepository(context);
        contactRepository.createContact(contact);
        Log.i("","¡Contacto creado satisfactoriamente!");
    }

    public void updateContact(Contact contact, Context context) {
        contactRepository = new ContactRepository(context);
        contactRepository.updateContact(contact);
        Log.i("","¡Contacto cambiado satisfactoriamente!");
    }

    public void deleteContact(int nit, Context context) {
        contactRepository = new ContactRepository(context);
        contactRepository.deleteContact(nit);
        Log.i("","¡Contacto eliminado satisfactoriamente!");
    }

    public List<Contact> getAllContacts(Context context){
        contactRepository = new ContactRepository(context);
        return contactRepository.getAllContacts();
    }

    public ContactRepository getContactRepository() {
        return contactRepository;
    }

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}
