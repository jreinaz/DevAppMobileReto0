package co.edu.unal.reto8.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.reto8.R;
import co.edu.unal.reto8.businessLogic.controller.ContactController;
import co.edu.unal.reto8.dataAccess.model.Contact;

public class MainActivity extends AppCompatActivity {

    private EditText mContactNit;
    private EditText mContactName;
    private EditText mContactWebPgURL;
    private EditText mContactPhone;
    private EditText mContactEmail;
    private EditText mContactProductsAndServices;
    private EditText mContactIsConsultant;
    private EditText mContactIsCustomDev;
    private EditText mContactIsSoftwareFactory;
    private Button mCreateContactButton;
    private Button mUpdateContactButton;
    private Button mDeleteContactButton;
    private Button mGetContactsButton;
    private ListView mGetAvailableContactsList;
    private ArrayList<String> contacts = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ContactController contactController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
        mContactNit = (EditText) findViewById(R.id.contact_nit_edit);
        mContactName = (EditText) findViewById(R.id.company_name_edit);
        mContactWebPgURL = (EditText) findViewById(R.id.web_page_edit);
        mContactPhone = (EditText) findViewById(R.id.contact_phone_edit);
        mContactEmail = (EditText) findViewById(R.id.contact_email_edit);
        mContactProductsAndServices = (EditText) findViewById(R.id.products_services_edit);
        mContactIsConsultant = (EditText) findViewById(R.id.is_consultant_edit);
        mContactIsCustomDev = (EditText) findViewById(R.id.is_custom_development_edit);
        mContactIsSoftwareFactory = (EditText) findViewById(R.id.is_software_factory_edit);
        mCreateContactButton = (Button) findViewById(R.id.create_contact_button);
        mUpdateContactButton = (Button) findViewById(R.id.update_contact_button);
        mDeleteContactButton = (Button) findViewById(R.id.delete_contact_button);
        mGetContactsButton = (Button) findViewById(R.id.available_contacts_button);
        mGetAvailableContactsList = (ListView) findViewById(R.id.available_contacts_list);
        mCreateContactButton.setEnabled(true);
        mCreateContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                contact.setNit(Integer.parseInt(mContactNit.getText().toString()));
                contact.setCompanyName(mContactName.getText().toString());
                contact.setWebPageURL(mContactWebPgURL.getText().toString());
                contact.setContactPhone(mContactPhone.getText().toString());
                contact.setContactEmail(mContactEmail.getText().toString());
                contact.setProductsAndServices(mContactProductsAndServices.getText().toString());
                contact.setConsultant(Boolean.parseBoolean(mContactIsConsultant.getText().toString()));
                contact.setCustomDevelopment(Boolean.parseBoolean(mContactIsCustomDev.getText().toString()));
                contact.setSoftwareFactory(Boolean.parseBoolean(mContactIsSoftwareFactory.getText().toString()));
                contactController = new ContactController();
                contactController.createContact(contact, getApplicationContext());
            }
        });
        mUpdateContactButton.setEnabled(true);
        mUpdateContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                contact.setNit(Integer.parseInt(mContactNit.getText().toString()));
                contact.setCompanyName(mContactName.getText().toString());
                contact.setWebPageURL(mContactWebPgURL.getText().toString());
                contact.setContactPhone(mContactPhone.getText().toString());
                contact.setContactEmail(mContactEmail.getText().toString());
                contact.setProductsAndServices(mContactProductsAndServices.getText().toString());
                contact.setConsultant(Boolean.parseBoolean(mContactIsConsultant.getText().toString()));
                contact.setCustomDevelopment(Boolean.parseBoolean(mContactIsCustomDev.getText().toString()));
                contact.setSoftwareFactory(Boolean.parseBoolean(mContactIsSoftwareFactory.getText().toString()));
                contactController = new ContactController();
                contactController.updateContact(contact, getApplicationContext());
            }
        });
        mDeleteContactButton.setEnabled(true);
        mDeleteContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nit = Integer.parseInt(mContactNit.getText().toString());
                contactController = new ContactController();
                contactController.deleteContact(nit, getApplicationContext());
            }
        });
        mGetContactsButton.setEnabled(true);
        mGetContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contacts.clear();
                contactController = new ContactController();
                ArrayList<Contact> contactsReceived = (ArrayList<Contact>) contactController.getAllContacts(getApplicationContext());
                for(int i = 0; i<contactsReceived.size(); i++){
                    Contact contact = contactsReceived.get(i);
                    contacts.add(String.valueOf(contact.nit)+" "+contact.companyName+" "+contact.contactEmail);
                }
                contactsReceived.clear();
                Log.i("TAMAÑO CONTACTOS: ", String.valueOf(contacts.size()));
                mGetAvailableContactsList.setAdapter(adapter);
            }
        });
    }
}