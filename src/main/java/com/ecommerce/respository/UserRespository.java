//Queries are defined in repository interfaces using method names or @Query annotations.
//Jpa give inbuilt functions only for CRUD operations(Inbuilt queries Create,Read,Update,Delete)..
// for complex queries we have to define our own methods.





package com.ecommerce.respository;
import org.spingframework.data.jpa.respository.JpaRepository;
import com.ecommerce.entity.User;
public class UserRespository extends JpaRepository<User, Long> { //JpaRepository is a JPA specific extension of Repository. It contains the full API of CrudRepository and PagingAndSortingRepository.
                                                              //It provides JPA related methods such as flushing the persistence context and delete records in a batch.
                                                              // User Long means the entity type is User and the primary key type is Long.

    // findByUsername(String username) -> Select * from users where username = ?
    // It should be public and return one user object or Optional<User>(After java 8 introduced,in util package) to handle null values.
    public Optional<User> findByUsername(String username); //If no user ,it will return null ...It will handles the nullPointer exception since 
    public Optional<User> findByEmail(String email);       //In user u1(without new its not an object just a var); there is a null...it doesn't throw error...instead it return null...
   
    //existsByUsername(String username) -> Select count(*) > 0 from users where username = ?
    // It should be public and return boolean value.
    public boolean existsByUsername(String username); //If give User alone it may throw error...this function replaces the query..
    public boolean existsByEmail(String email);
    

    //Custom query using @Query annotation
    //If more than 3 words exists we have to use @Query annotation to define our own query.
    //each user is considered as u... 
    //first row is u, u.username andha row la iruka username
    @Query("SELECT u FROM User u WHERE u.username =?1 OR u.email = ?2")  
    Optional<User> findByUsernameOrEmail(String username,String email); //It will search for user by username or email

}
