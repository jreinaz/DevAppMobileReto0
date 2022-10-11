package co.edu.unal.reto8.dataAccess.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    @ColumnInfo(name = "NIT")
    @PrimaryKey
    public int nit;

    @ColumnInfo(name = "company_name")
    public String companyName;

    @ColumnInfo(name = "web_page_url")
    public String webPageURL;

    @ColumnInfo(name = "contact_phone")
    public String contactPhone;

    @ColumnInfo(name = "contact_email")
    public String contactEmail;

    @ColumnInfo(name = "products_services")
    public String productsAndServices;

    @ColumnInfo(name = "isConsultant")
    public boolean isConsultant;

    @ColumnInfo(name = "isCustomDevelopment")
    public boolean isCustomDevelopment;

    @ColumnInfo(name = "isSoftwareFactory")
    public boolean isSoftwareFactory;

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebPageURL() {
        return webPageURL;
    }

    public void setWebPageURL(String webPageURL) {
        this.webPageURL = webPageURL;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getProductsAndServices() {
        return productsAndServices;
    }

    public void setProductsAndServices(String productsAndServices) {
        this.productsAndServices = productsAndServices;
    }

    public boolean isConsultant() {
        return isConsultant;
    }

    public void setConsultant(boolean consultant) {
        isConsultant = consultant;
    }

    public boolean isCustomDevelopment() {
        return isCustomDevelopment;
    }

    public void setCustomDevelopment(boolean customDevelopment) {
        isCustomDevelopment = customDevelopment;
    }

    public boolean isSoftwareFactory() {
        return isSoftwareFactory;
    }

    public void setSoftwareFactory(boolean softwareFactory) {
        isSoftwareFactory = softwareFactory;
    }
}
