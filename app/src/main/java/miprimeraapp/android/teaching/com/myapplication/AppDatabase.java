package miprimeraapp.android.teaching.com.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {user.class}, version = 1)
// room DB
public abstract class AppDatabase extends RoomDatabase{
    public abstract UserDao userDao();

}
