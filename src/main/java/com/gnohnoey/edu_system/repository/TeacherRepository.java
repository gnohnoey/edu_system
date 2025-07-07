package com.gnohnoey.edu_system.repository;

import com.gnohnoey.edu_system.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {
    private final JdbcTemplate jdbcTemplate;

    //R
    private final RowMapper<Teacher> mapper = (resultSet, rowNum) ->
            Teacher.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();
    //R
    public List<Teacher> findAll(){
        return jdbcTemplate.query("SELECT * FROM teacher ORDER BY NAME", mapper);
    }

    public Teacher findById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM teacher WHERE id = ?", mapper, id);
    }

    //C
    public int save(Teacher teacher){
        return jdbcTemplate.update(
                //teacher 테이블에 있는 name 컬럼에 값을 하나 새로 추가(insert) 하겠다는 뜻
                "INSERT INTO teacher (name) VALUES (?)", teacher.getName() //.getName은 GETTER 메서드
                                                                               // Teacher 클래스에 @Data를 선언해서 GETTER나 SETTER를 따로 설정하지 않아도 불러올 수 있음

        );
    }

    //U
    public int update(Teacher teacher){
        return jdbcTemplate.update(
                "UPDATE teacher SET name = ? WHERE id = ?", teacher.getName(), teacher.getId()
        );
    }

    //D
    public int deleteId(int id){
        return jdbcTemplate.update(
                "DELETE FROM teacher WHERE id = ?", id //id가 유니크한 값이기 때문에 생성과 삭제가 쉽고 효율적임. 그래서 id를 통해 삭제하는 것!
        );
    }

    //jdbcTemplate에서 update가 반환하는 것은 db에서 처리된 데이터 행

}
