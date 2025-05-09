DROP DATABASE IF EXISTS spring_25_05;
CREATE DATABASE spring_25_05;
USE spring_25_05;

# 게시글 테이블 생성
CREATE TABLE article (
							 id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
							 regDate DATETIME NOT NULL,
							 updateDate DATETIME NOT NULL,
							 title CHAR(100) NOT NULL,
							 `body` TEXT NOT NULL
);

# 회원 테이블 생성
CREATE TABLE `member` (
							 id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
							 regDate DATETIME NOT NULL,
							 updateDate DATETIME NOT NULL,
							 loginId CHAR(100) NOT NULL,
							 loginPw CHAR(200) NOT NULL,
							 `authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한 레벨 (3=일반, 7=관리자)',
							 `name` CHAR(20) NOT NULL,
							 nickname CHAR(20) NOT NULL,
							 cellphoneNum CHAR(20) NOT NULL,
							 email CHAR(50) NOT NULL,
							 delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴 여부 (0=탈퇴 전, 1=탈퇴 후)',
							 delDate DATETIME COMMENT '탈퇴 날짜' 
);

# 게시글 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';


# 회원 테스트 데이터 생성

#관리자
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`authLevel` = 7,
`name` = '관리자',
nickname = '관리자_닉네임',
cellphoneNum = '01012341234',
email = 'abc@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '회원1',
nickname = '피카츄',
cellphoneNum = '01012341234',
email = 'pikachyu@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '회원2',
nickname = '파이리',
cellphoneNum = '01056785678',
email = 'paili@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test3',
loginPw = 'test3',
`name` = '회원3',
nickname = '꼬부기',
cellphoneNum = '01078787878',
email = 'kkobugi@gmail.com';

ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

UPDATE article
SET memberId = 2
WHERE id IN(1,2);

UPDATE article
SET memberId = 3
WHERE id  = 3;

SELECT *
FROM article
ORDER BY id DESC;

SELECT * FROM `member`;




######################################################################

select * from `member`
where loginId = 'test4'

select ceiling(RAND() * 3);

# 게시글 데이터 대량 생성
INSERT INTO article
SET regDate = NOW(),
memberId = ceiling(RAND() * 3),
title = CONCAT('제목__', rand()),
`body` = CONCAT('내용__',rand());



SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'test2';

SELECT 1 + 1;
SELECT 1 >= 1;

SELECT COUNT(*) > 0 FROM `member` WHERE loginId = 'test3';

SELECT NOW();

SELECT '제목1';

SELECT CONCAT('제목',' 1');

SELECT SUBSTRING(RAND() * 1000 FROM 1 FOR 2);

INSERT INTO articleset regDate = NOW(),updateDate = NOW(),title = CONCAT('제목',SUBSTRING(RAND() * 1000 FROM 1 FOR 2)),`body` = CONCAT('내용',SUBSTRING(RAND() * 1000 FROM 1 FOR 2));



UPDATE article
SET updateDate = NOW(),
 title = 'title1',
 `body` = 'body1'
WHERE id = 3;

UPDATE article
SET updateDate = NOW(),
 `body` = 'body1'
WHERE id = 1;

SELECT * FROM article;

SELECT COUNT(*)
FROM article
WHERE id = 5;

UPDATE article
SET updateDate = NOW(),
 title = 'title1',
 `body` = 'body1'
WHERE id = 5;