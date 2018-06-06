package miprimeraapp.android.teaching.com.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    //comandos de bases de datos
    @Query("SELECT * FROM user WHERE username IS :username")
    user findByUsername(String username);
    @Insert
    void insert(user user);
    @Delete
    void delete(user user);
}
