package com.junstudio.kickoff.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("backdoor")
@Transactional
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    private final PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate,
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

        jdbcTemplate.update("" +
                "INSERT INTO person" +
                "(user_id, identification, encoded_password, name, profile_image, user_grade)" +
                " VALUES(1, ?, ?, 'son7'," +
                " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/7fb14f2b-f348-426b-a9af-c54e410941da%E1%84%91%E1%85%B5%E1%84%8F%E1%85%A1%E1%84%8E%E1%85%B2.jpeg'," +
                " '아마추어')"
            , "jel1y", passwordEncoder.encode("Qwe1234!")
        );

        jdbcTemplate.update("" +
                "INSERT INTO person" +
                "(user_id, identification, encoded_password, name, profile_image, user_grade)" +
                " VALUES(2, ?, ?, 'Jun'," +
                " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/7fb14f2b-f348-426b-a9af-c54e410941da%E1%84%91%E1%85%B5%E1%84%8F%E1%85%A1%E1%84%8E%E1%85%B2.jpeg'," +
                " '아마추어')"
            , "stw550", passwordEncoder.encode("Qwe1234!")
        );

        jdbcTemplate.execute("" +
            "INSERT INTO post" +
            "(post_id, post_title, post_content, user_id, board_id, hit, image_url, created_at)" +
            " VALUES(1, '카타르 월드컵 개최 일주일 전', '월드컵 기대가 됩니다.', 1, 1, 10," +
            " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/aac172c5-45bf-47f9-aabd-6ff671204bfaLaLiga.jpg'," +
            " '2022-11-14')"
        );

        jdbcTemplate.execute("" +
            "INSERT INTO comment" +
            "(comment_id, comment_date, content, post_id, user_id, is_deleted)" +
            " VALUES(1, '2022-11-14', '대한민국 16강 응원합니다.', 1, 1, 'false')"
        );

        return "OK";
    }

    @GetMapping("setting-posts")
    public String settingPosts() {
        jdbcTemplate.execute("DELETE FROM likes");
        jdbcTemplate.execute("DELETE FROM recomment");
        jdbcTemplate.execute("DELETE FROM comment");
        jdbcTemplate.execute("DELETE FROM post");

        for (long i = 1; i <= 101; i += 1) {
            jdbcTemplate.update("" +
                    "INSERT INTO post" +
                    "(post_id, post_title, post_content, user_id, board_id, hit, image_url, created_at)" +
                    " VALUES(?, '카타르 월드컵 개최 일주일 전', '월드컵 기대가 됩니다.', 1, 1, 10," +
                    " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/aac172c5-45bf-47f9-aabd-6ff671204bfaLaLiga.jpg'," +
                    " '2022-11-14')"
                , i
            );
        }

        return "ok";
    }

    @GetMapping("setting-posts10")
    public String settingPostsTen() {
        jdbcTemplate.execute("DELETE FROM likes");
        jdbcTemplate.execute("DELETE FROM recomment");
        jdbcTemplate.execute("DELETE FROM comment");
        jdbcTemplate.execute("DELETE FROM post");

        for (long i = 1; i <= 11; i += 1) {
            jdbcTemplate.update("" +
                    "INSERT INTO post" +
                    "(post_id, post_title, post_content, user_id, board_id, hit, image_url, created_at)" +
                    " VALUES(?, '카타르 월드컵 개최 일주일 전', '월드컵 기대가 됩니다.', 1, 1, 10," +
                    " 'https://kickoffproject.s3.ap-northeast-2.amazonaws.com/kickoffproject/aac172c5-45bf-47f9-aabd-6ff671204bfaLaLiga.jpg'," +
                    " '2022-11-14')"
                , i
            );
        }

        for (long i = 1; i <= 21; i += 1) {
            jdbcTemplate.update("" +
                    "INSERT INTO comment" +
                    "(comment_id, comment_date, content, post_id, user_id, is_deleted)" +
                    " VALUES(?, '2022-11-14', '대한민국 16강 응원합니다.', 1, 1, 'false')"
                , i
            );
        }

        return "ok";
    }

    @GetMapping("setting-recomments")
    public String settingRecomment() {
        jdbcTemplate.execute("" +
            "INSERT INTO recomment" +
            "(recomment_id, comment_date, comment_id, content, post_id, user_id)" +
            " VALUES(1, '2022-11-14', 1, '16강 가능할까?', 1, 1)"
        );

        return "Ok";
    }
}
