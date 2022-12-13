package com.junstudio.kickoff.admin.backdoor;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
@RequestMapping("admin-backdoor")
@Transactional
public class AdminBackdoorController {
    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    public AdminBackdoorController(JdbcTemplate jdbcTemplate,
                              PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {
        jdbcTemplate.execute("DELETE FROM likes");
        jdbcTemplate.execute("DELETE FROM recomment");
        jdbcTemplate.execute("DELETE FROM comment");
        jdbcTemplate.execute("DELETE FROM post");
        jdbcTemplate.execute("DELETE FROM person");
        jdbcTemplate.execute("DELETE FROM application_post");

        jdbcTemplate.update("" +
                "INSERT INTO person" +
                "(user_id, identification, encoded_password, name, profile_image, user_grade, created_at)" +
                " VALUES(2, ?, ?, '짱구'," +
                " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/7fb14f2b-f348-426b-a9af-c54e410941da%E1%84%91%E1%85%B5%E1%84%8F%E1%85%A1%E1%84%8E%E1%85%B2.jpeg'," +
                " '아마추어', ?)"
            , "jjanggu", passwordEncoder.encode("Qwe1234!"), LocalDateTime.now()
        );

        jdbcTemplate.update("" +
                "INSERT INTO person" +
                "(user_id, identification, encoded_password, name, profile_image, user_grade, created_at)" +
                " VALUES(3, ?, ?, '맹구'," +
                " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/7fb14f2b-f348-426b-a9af-c54e410941da%E1%84%91%E1%85%B5%E1%84%8F%E1%85%A1%E1%84%8E%E1%85%B2.jpeg'," +
                " '아마추어', ?)"
            , "maenggu", passwordEncoder.encode("Qwe1234!"), LocalDateTime.now()
        );

        for (long i = 1; i <= 10; i += 1) {
            jdbcTemplate.update("" +
                    "INSERT INTO post" +
                    "(post_id, post_title, post_content, user_id, board_id, hit, image_url, created_at)" +
                    " VALUES(?, '카타르 월드컵 개최 일주일 전', '월드컵 기대가 됩니다.', 1, 2, 10," +
                    " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/aac172c5-45bf-47f9-aabd-6ff671204bfaLaLiga.jpg'," +
                    " ?)"
                , i, LocalDateTime.now()
            );
        }

        for (long i = 1; i <= 5; i += 1) {
            jdbcTemplate.update("" +
                    "INSERT INTO comment" +
                    "(comment_id, comment_date, content, post_id, user_id, is_deleted)" +
                    " VALUES(?, ?, '대한민국 16강 응원합니다.', 1, 1, 'false')"
                , i, LocalDateTime.now()
            );
        }

        jdbcTemplate.execute("" +
            "INSERT INTO application_post" +
            "(application_id, application_grade, current_grade, name, comment_number, post_number, reason, state)" +
            " VALUES(1, '세미프로', '프로', '짱구', 10, 10, '테스트', 'processing')"
        );

        return "OK";
    }

    @GetMapping("setup-account")
    public String setupAdminAccount() {
        jdbcTemplate.execute("DELETE FROM admin");

        jdbcTemplate.update("" +
                "INSERT INTO admin " +
                "(admin_id, identification, encoded_password, name, profile_image)" +
                " VALUES(2, ?, ?, '짱구'," +
                " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/7fb14f2b-f348-426b-a9af-c54e410941da%E1%84%91%E1%85%B5%E1%84%8F%E1%85%A1%E1%84%8E%E1%85%B2.jpeg')"
            , "jel1y", passwordEncoder.encode("Qwe1234!")
        );

        return "ok";
    }
}
