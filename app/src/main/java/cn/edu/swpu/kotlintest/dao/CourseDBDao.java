package cn.edu.swpu.kotlintest.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import cn.edu.swpu.kotlintest.entity.CourseDB;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COURSE_DB".
*/
public class CourseDBDao extends AbstractDao<CourseDB, Void> {

    public static final String TABLENAME = "COURSE_DB";

    /**
     * Properties of entity CourseDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CourseId = new Property(0, String.class, "courseId", false, "COURSE_ID");
        public final static Property CourseName = new Property(1, String.class, "courseName", false, "COURSE_NAME");
        public final static Property CourseNum = new Property(2, String.class, "courseNum", false, "COURSE_NUM");
        public final static Property Credit = new Property(3, String.class, "credit", false, "CREDIT");
        public final static Property Properties = new Property(4, String.class, "properties", false, "PROPERTIES");
        public final static Property Type = new Property(5, String.class, "type", false, "TYPE");
        public final static Property Teacher = new Property(6, String.class, "teacher", false, "TEACHER");
        public final static Property StudyMode = new Property(7, String.class, "StudyMode", false, "STUDY_MODE");
        public final static Property State = new Property(8, String.class, "state", false, "STATE");
        public final static Property Week = new Property(9, String.class, "week", false, "WEEK");
        public final static Property Weekday = new Property(10, String.class, "weekday", false, "WEEKDAY");
        public final static Property StartTime = new Property(11, String.class, "startTime", false, "START_TIME");
        public final static Property Duration = new Property(12, String.class, "duration", false, "DURATION");
        public final static Property Campus = new Property(13, String.class, "campus", false, "CAMPUS");
        public final static Property TeachingBuilding = new Property(14, String.class, "teachingBuilding", false, "TEACHING_BUILDING");
        public final static Property Classroom = new Property(15, String.class, "classroom", false, "CLASSROOM");
    }


    public CourseDBDao(DaoConfig config) {
        super(config);
    }
    
    public CourseDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COURSE_DB\" (" + //
                "\"COURSE_ID\" TEXT," + // 0: courseId
                "\"COURSE_NAME\" TEXT," + // 1: courseName
                "\"COURSE_NUM\" TEXT," + // 2: courseNum
                "\"CREDIT\" TEXT," + // 3: credit
                "\"PROPERTIES\" TEXT," + // 4: properties
                "\"TYPE\" TEXT," + // 5: type
                "\"TEACHER\" TEXT," + // 6: teacher
                "\"STUDY_MODE\" TEXT," + // 7: StudyMode
                "\"STATE\" TEXT," + // 8: state
                "\"WEEK\" TEXT," + // 9: week
                "\"WEEKDAY\" TEXT," + // 10: weekday
                "\"START_TIME\" TEXT," + // 11: startTime
                "\"DURATION\" TEXT," + // 12: duration
                "\"CAMPUS\" TEXT," + // 13: campus
                "\"TEACHING_BUILDING\" TEXT," + // 14: teachingBuilding
                "\"CLASSROOM\" TEXT);"); // 15: classroom
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COURSE_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CourseDB entity) {
        stmt.clearBindings();
 
        String courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindString(1, courseId);
        }
 
        String courseName = entity.getCourseName();
        if (courseName != null) {
            stmt.bindString(2, courseName);
        }
 
        String courseNum = entity.getCourseNum();
        if (courseNum != null) {
            stmt.bindString(3, courseNum);
        }
 
        String credit = entity.getCredit();
        if (credit != null) {
            stmt.bindString(4, credit);
        }
 
        String properties = entity.getProperties();
        if (properties != null) {
            stmt.bindString(5, properties);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(6, type);
        }
 
        String teacher = entity.getTeacher();
        if (teacher != null) {
            stmt.bindString(7, teacher);
        }
 
        String StudyMode = entity.getStudyMode();
        if (StudyMode != null) {
            stmt.bindString(8, StudyMode);
        }
 
        String state = entity.getState();
        if (state != null) {
            stmt.bindString(9, state);
        }
 
        String week = entity.getWeek();
        if (week != null) {
            stmt.bindString(10, week);
        }
 
        String weekday = entity.getWeekday();
        if (weekday != null) {
            stmt.bindString(11, weekday);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(12, startTime);
        }
 
        String duration = entity.getDuration();
        if (duration != null) {
            stmt.bindString(13, duration);
        }
 
        String campus = entity.getCampus();
        if (campus != null) {
            stmt.bindString(14, campus);
        }
 
        String teachingBuilding = entity.getTeachingBuilding();
        if (teachingBuilding != null) {
            stmt.bindString(15, teachingBuilding);
        }
 
        String classroom = entity.getClassroom();
        if (classroom != null) {
            stmt.bindString(16, classroom);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CourseDB entity) {
        stmt.clearBindings();
 
        String courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindString(1, courseId);
        }
 
        String courseName = entity.getCourseName();
        if (courseName != null) {
            stmt.bindString(2, courseName);
        }
 
        String courseNum = entity.getCourseNum();
        if (courseNum != null) {
            stmt.bindString(3, courseNum);
        }
 
        String credit = entity.getCredit();
        if (credit != null) {
            stmt.bindString(4, credit);
        }
 
        String properties = entity.getProperties();
        if (properties != null) {
            stmt.bindString(5, properties);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(6, type);
        }
 
        String teacher = entity.getTeacher();
        if (teacher != null) {
            stmt.bindString(7, teacher);
        }
 
        String StudyMode = entity.getStudyMode();
        if (StudyMode != null) {
            stmt.bindString(8, StudyMode);
        }
 
        String state = entity.getState();
        if (state != null) {
            stmt.bindString(9, state);
        }
 
        String week = entity.getWeek();
        if (week != null) {
            stmt.bindString(10, week);
        }
 
        String weekday = entity.getWeekday();
        if (weekday != null) {
            stmt.bindString(11, weekday);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(12, startTime);
        }
 
        String duration = entity.getDuration();
        if (duration != null) {
            stmt.bindString(13, duration);
        }
 
        String campus = entity.getCampus();
        if (campus != null) {
            stmt.bindString(14, campus);
        }
 
        String teachingBuilding = entity.getTeachingBuilding();
        if (teachingBuilding != null) {
            stmt.bindString(15, teachingBuilding);
        }
 
        String classroom = entity.getClassroom();
        if (classroom != null) {
            stmt.bindString(16, classroom);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public CourseDB readEntity(Cursor cursor, int offset) {
        CourseDB entity = new CourseDB( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // courseId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // courseName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // courseNum
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // credit
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // properties
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // type
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // teacher
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // StudyMode
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // state
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // week
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // weekday
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // startTime
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // duration
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // campus
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // teachingBuilding
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15) // classroom
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CourseDB entity, int offset) {
        entity.setCourseId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setCourseName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCourseNum(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCredit(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProperties(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setType(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setTeacher(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setStudyMode(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setState(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setWeek(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setWeekday(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setStartTime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setDuration(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setCampus(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setTeachingBuilding(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setClassroom(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(CourseDB entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(CourseDB entity) {
        return null;
    }

    @Override
    public boolean hasKey(CourseDB entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
