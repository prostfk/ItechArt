package by.itechart.test.dao;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.FamilyStatus;
import by.itechart.contacts.model.entity.Gender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class ContactDaoTest {

    private ContactDao contactDao;

    @Before
    public void setUp() throws Exception {
        contactDao = new ContactDao();
    }

    @Test
    public void findByIdTest() {
        Contact contactById = contactDao.findContactById(2L);
        Contact Roman = new Contact(2L, "Roman", "Medvedev", "Sergeevich", "2000-01-01", Gender.Male, "Belarus", FamilyStatus.Single, "vk.com/prostrmk", "prostrmk@gmail.com", "none", 0L);
        assertEquals(Roman,contactById);
    }
}
