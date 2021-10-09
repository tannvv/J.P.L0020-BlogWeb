
CREATE TABLE Account(
	email varchar(30),
	password varchar(20),
	status int
	PRIMARY KEY(email)
)
CREATE TABLE Role(
	roleID int,
	roleName varchar(20)
	PRIMARY KEY (roleID)
)
CREATE TABLE InforUser(
	inforID varchar(30) UNIQUE FOREIGN KEY REFERENCES Account(email),
	userName varchar(30),
	birthDay date,
	roleID int
	FOREIGN KEY (roleID) REFERENCES Role(roleID) ON UPDATE CASCADE ON DELETE CASCADE
)
CREATE TABLE Article(
	articleID int IDENTITY(1,1),
	title nvarchar(100),
	shortDescription nvarchar(200),
	contentArticle nvarchar(MAX),
	postDate date,
	author nvarchar(30),
	status int,
	userID varchar(30)
	PRIMARY KEY (articleID) REFERENCES Account(email)
)
CREATE TABLE Comment(
	commentID bigint IDENTITY(1,1),
	commentDate date,
	contentComment nvarchar(MAX),
	articleID int,
	userID varchar(30),
	PRIMARY KEY(commentID),
	FOREIGN KEY (articleID) REFERENCES Article(articleID) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (userID) REFERENCES Account(email) ON UPDATE CASCADE ON DELETE CASCADE
)
