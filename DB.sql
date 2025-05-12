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
title = '귀멸의 칼날',
`body` = '물의 호흡';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '포켓몬스터',
`body` = '피카츄';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '짱구는 못말려',
`body` = '짱구';


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
nickname = '탄지로',
cellphoneNum = '01012341234',
email = 'tanjiro@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '회원2',
nickname = '피카츄',
cellphoneNum = '01056785678',
email = 'pikachyu@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test3',
loginPw = 'test3',
`name` = '회원3',
nickname = '짱구',
cellphoneNum = '01078787878',
email = 'shinJJang@gmail.com';

ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

UPDATE article
SET memberId = 2
WHERE id = 1;

UPDATE article
SET memberId = 3
WHERE id = 2;

UPDATE article
SET memberId = 4
WHERE id  = 3;

SELECT *
FROM article
ORDER BY id DESC;

SELECT * FROM `member`;


SELECT A.*, M.nickname AS extra__writer
		FROM article AS A
		INNER JOIN `member` AS M
		ON A.memberId = M.id
		WHERE A.id = 1;
		
SELECT A.*, M.nickname AS extra__writer 
		FROM article AS A
		INNER JOIN `member` AS M
		ON A.memberId = M.id
		ORDER BY A.id DESC;

######################################################################

SELECT * FROM `member`
WHERE loginId = 'test4'

SELECT CEILING(RAND() * 3);

# 게시글 데이터 대량 생성
INSERT INTO article
SET regDate = NOW(),
memberId = CEILING(RAND() * 3),
title = CONCAT('제목__', RAND()),
`body` = CONCAT('내용__',RAND());



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