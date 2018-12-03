package learn.epam.mlhh.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Database entity classes for logs.
 * @author
 * @version 1.1.2
 */
@Entity
@Table(name = "Log")
public class Log {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long logId;

        @Column
        private Date date;

        @Column
        private Time time;

        @Column(columnDefinition="text")
        private String  status;

        @Column(columnDefinition="text")
        private String className;

        @Column(columnDefinition="text")
        private String message;

        public Log() {
        }

        public Long getLogId() {
            return logId;
        }
        public void setLogId(Long logId) {
            this.logId = logId;
        }

        public  Date getDate() {return  date;}
        public void setDate(Date date) {this.date = date;}

        public  Time getTime() { return time;}
        public void setTime(Time time) { this.time = time;}

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }

        public String getClassName() {
            return className;
        }
        public void setClassName(String className) {
            this.className = className;
        }

        public String getMessage() {
            return message;
        }
        public void setMessage(String message) { this.message = message; }


        @Override
        public String toString() {
            return "Log{" +
                    "logId=" + logId +
                    ", date='" + date + '\'' +
                    ", time='" + time + '\'' +
                    ", status='" + status + '\'' +
                    ", className='" + className + '\'' +
                    ", message='" + message +
                    '}';
        }
}



