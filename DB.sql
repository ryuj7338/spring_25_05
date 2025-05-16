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

# 게시판 테이블 생성
CREATE TABLE board (
							 id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
							 regDate DATETIME NOT NULL,
							 updateDate DATETIME NOT NULL,
							 `code` CHAR(100) NOT NULL UNIQUE COMMENT '공지사항 자유게시판 Q&A',
							 `name` CHAR(100) NOT NULL UNIQUE COMMENT '게시판 이름',
							 delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제 여부 (0=삭제 전, 1=삭제 후)',
							 delDate DATETIME COMMENT '삭제 날짜' 
);

# 좋아요 테이블 생성
CREATE TABLE `like` ( 
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL, 
	memberId INT(10) UNSIGNED NOT NULL,
	relTypeCode CHAR(100) NOT NULL COMMENT '관련 데이터 타입 코드',
	relId INT(10) NOT NULL COMMENT '관련 데이터 번호',
	`like` INT(10) NOT NULL
	);

ALTER TABLE `like` MODIFY COLUMN `like` INT(10) NOT NULL AFTER relId;

DESC `like`;

SELECT * FROM `like`;


# LEFT JOIN(을 해야 전체 게시글에 좋아요를 알 수 있음 / null도 포함)
# INNER JOIN -> null 제외한 게시글만 나옴
SELECT A.*, M.nickname AS extra__writer, SUM(L.like) AS `totalLike`,
FROM article A
INNER JOIN `member` M
ON A.memberId = M.id
LEFT JOIN `like` L
ON A.id = L.relId AND L.relTypeCode = 'article'
GROUP BY A.id
ORDER BY A.id DESC;

#서브쿼리
SELECT A.*, 
IFNULL(SUM(L.like), 0) AS `TotalLike`, 
IFNULL(SUM(IF(L.like > 0, L.like , 0)), 0) AS `Like`,
IFNULL(SUM(IF(L.like < 0 , L.like, 0)), 0) AS `DisLike`
FROM article A
LEFT JOIN `like` L
ON A.id = L.relId AND L.relTypeCode = 'article'
GROUP BY A.id
ORDER BY A.id DESC;


#JOIN
SELECT A.*, M.nickname AS extra__writer,
IFNULL(SUM(L.like), 0) AS TotalLike, 
IFNULL(SUM(IF(L.like > 0, L.like , 0)), 0) AS `Like`,
IFNULL(SUM(IF(L.like < 0 , L.like, 0)), 0) AS DisLike
FROM article A
INNER JOIN `member` M
ON A.memberId = M.id
LEFT JOIN `like` L
ON A.id = L.relId AND L.relTypeCode = 'article'
GROUP BY A.id
ORDER BY A.id DESC;



# 좋아요 테스트 데이터 생성
# 1번 회원이 1번 글에 싫어요
INSERT INTO `like`
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'article',
relId = 1,
`like` = -1;

#1번 회원이 2번 글에 좋아요
INSERT INTO `like`
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'article',
relId = 2,
`like` = 1;

#2번 회원이 1번 글에 싫어요
INSERT INTO `like`
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relTypeCode = 'article',
relId = 1,
`like` = -1;

#2번 회원이 2번 글에 싫어요
INSERT INTO `like`
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relTypeCode = 'article',
relId = 2,
`like` = -1;

#3번 회원이 1번 글에 좋아요
INSERT INTO `like`
SET regDate = NOW(),
updateDate = NOW(),
memberId = 3,
relTypeCode = 'article',
relId = 1,
`like` = 1;

# 게시판 테스트 데이터 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = '공지사항',
`name` = '공지';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = '자유게시판',
`name` = '자유글';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'Q&A',
`name` = 'Q&A';


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
`name` = '탄지로',
nickname = '탄지로',
cellphoneNum = '01012341234',
email = 'tanjiro@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '피카츄',
nickname = '피카츄',
cellphoneNum = '01056785678',
email = 'pikachyu@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test3',
loginPw = 'test3',
`name` = '짱구',
nickname = '짱구',
cellphoneNum = '01078787878',
email = 'shinJJang@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test4',
loginPw = 'test4',
`name` = '맹구',
nickname = '맹구',
cellphoneNum = '01022222222',
email = 'maenggu@gmail.com';

## 멤버 아이디 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

## 게시판 번호 추가
ALTER TABLE article ADD COLUMN boardId INT(10) NOT NULL AFTER `memberId`;

SELECT * FROM board;

UPDATE article
SET memberId = 2
WHERE id = 1;

UPDATE article
SET memberId = 3
WHERE id = 2;

UPDATE article
SET memberId = 4
WHERE id  = 3;

UPDATE article
SET memberId = 5
WHERE id = 4;

SELECT *
FROM article
ORDER BY id DESC;

SELECT * FROM `member`;

UPDATE article
SET boardId = 2;

# 검색
# 개수
SELECT COUNT(*)
FROM article
WHERE boardId = 2 AND title LIKE '%asd%'
OR `body` LIKE '%asd%';


# 모두 보여주기
SELECT *
FROM article
WHERE boardId =  AND title LIKE '%귀%';

#조회수 추가
ALTER TABLE article ADD COLUMN hit INT(10) NOT NULL;

UPDATE article
		SET
		hit = hit + 1
		WHERE id = 1;




###########################

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


SELECT A.*, M.nickname
FROM article A
INNER JOIN `member` M
ON A.memberId = M.id
WHERE M.nickname LIKE '%구%'

DESC article

ALTER TABLE article MODIFY COLUMN hit INT(10) NOT NULL DEFAULT 0;