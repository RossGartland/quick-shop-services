package com.cn.partnerservice.repositories;
import com.cn.partnerservice.models.Owner;
import com.cn.partnerservice.models.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    /**
     * Deletes a store corresponding to the provided store ID.
     */
    @Test
    public void createStore()  {
        Store store = Store.builder()
                .storeName("Test Name")
                .phoneNumber("01632 945732")
                .emailAddress("Johnsshop@cntestmailuu.com")
                .address("34 Oak Lane, Banbridge, County Down, BT41 7CN")
                .latitude(54.10221896056039)
                .build();

        storeRepository.save(store);

        org.assertj.core.api.Assertions.assertThat(store.getStoreID()).isGreaterThan(0);
    }

    /**
     * Finds a store corresponding to the provided ID.
     */
    @Test
    void findById() {
        Store store = storeRepository.findById(1);
        org.assertj.core.api.Assertions.assertThat(store.getStoreID()).isGreaterThan(0);

    }

    /**
     * Gets all stores that are owned by the provided ownerID.
     */
    @Test
    void findAllByOwners()  {
        Set<Owner> owners = storeRepository.findById(4).getOwners();
        org.assertj.core.api.Assertions.assertThat(owners.size()).isGreaterThan(0);

    }

    /**
     * Deletes a store by providing a specified store ID.
     */
    @Test
    void deleteById() {
        Store store = Store.builder()
                .storeName("Test Name")
                .phoneNumber("01632 945732")
                .emailAddress("Johnsshop@cntestmailuu.com")
                .address("34 Oak Lane, Banbridge, County Down, BT41 7CN")
                .latitude(54.10221896056039)
                .build();

        store = storeRepository.save(store);

        storeRepository.deleteById(store.getStoreID());
        org.assertj.core.api.Assertions.assertThat(storeRepository.findById(store.getStoreID())).isNull();
    }

    /**
     * Edits a store by providing a store object.
     */
    @Test
    void editById() {
        Store store = Store.builder()
                .storeName("Test Name")
                .phoneNumber("01632 945732")
                .emailAddress("Johnsshop@cntestmailuu.com")
                .address("34 Oak Lane, Banbridge, County Down, BT41 7CN")
                .latitude(54.10221896056039)
                .build();

        store = storeRepository.save(store);
        store.setStoreName("Edited store name");
        storeRepository.save(store);

        assertEquals("Edited store name", store.getStoreName());
    }


}