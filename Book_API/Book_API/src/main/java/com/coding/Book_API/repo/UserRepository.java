package com.coding.Book_API.repo;


import com.coding.Book_API.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {


    Users findByEmailIdAndPassword(String emailID, String password);
}
