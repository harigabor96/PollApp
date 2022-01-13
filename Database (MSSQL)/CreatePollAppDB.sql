CREATE DATABASE PollApp;
GO

USE PollApp;
GO

CREATE TABLE dbo.Role
(
   Role varchar(20) NOT NULL CHECK (Len(RTrim([Role])) > 0),

   Primary Key (Role)
);
GO

CREATE TABLE dbo.AppUser
 (  
   UserID int identity(1,1) NOT NULL,   
   Username nvarchar(50) NOT NULL UNIQUE CHECK (Len(RTrim([Username])) > 0),
   Password char(60) NOT NULL,
   Role varchar(20) NOT NULL,
    
   Primary Key (UserID),
   Foreign Key (Role) REFERENCES Role(Role)
);  

GO

CREATE TABLE dbo.Question
 (  
   QuestionID int identity(1,1) NOT NULL,
   Question nvarchar(MAX) NOT NULL CHECK (Len(RTrim([Question])) > 0),
   CreatorID int NOT NULL,

   Primary Key (QuestionID),
   Foreign Key (CreatorID) REFERENCES AppUser(UserID)
);
GO

CREATE TABLE dbo.Answer
 (  
   AnswerID int identity(1,1) NOT NULL,
   QuestionID int NOT NULL,
   Answer nvarchar(MAX) NOT NULL CHECK (Len(RTrim([Answer])) > 0),

   primary key (AnswerID),
   Foreign Key (QuestionID) REFERENCES Question(QuestionID)
);
GO

CREATE TABLE dbo.Vote
 (  
   UserID int NOT NULL,
   AnswerID int NOT NULL,
   QuestionID int NOT NULL,

   Primary Key (UserID, QuestionID),
   Foreign Key (UserID) REFERENCES AppUser(UserID),
   Foreign Key (AnswerID) REFERENCES Answer(AnswerID),
   Foreign Key (QuestionID) REFERENCES Question(QuestionID)
);
GO

/*Define basic roles */
INSERT INTO dbo.Role(Role)
VALUES
	('ROLE_ADMIN'),
	('ROLE_USER');
GO

INSERT INTO dbo.AppUser(Username, Password, Role)
VALUES
	('admin', '$2a$10$/86Pemn5lXrPzO94j9CP8.iYQvlcPykuPXKAF307QvcO6Upc6/Myq', 'ROLE_ADMIN');
GO

/* Sample questions and answers */
INSERT INTO dbo.Question(Question, CreatorID)
VALUES
	('What is your name?', 1),
	('What is your favorite colour?', 1),
	('What is the capital of Assyria?', 1),
	('What is the airspeed velocity of an unladen swallow?', 1);
GO

INSERT INTO dbo.Answer(QuestionID, Answer)
VALUES
	(1, 'My name is Sir Lancelot of Camelot!'),
	(1, 'Sir Robin of Camelot!'),
	(1, 'Sir Galahad of Camelot!'),
	(1, 'It is Arthur, King of the Britons!'),
	(2, 'Blue!'),
	(2, 'Blue! ... No!'),
	(3, 'I don''t know that'),
	(4, 'What do you mean?'),	
	(4, 'An african or european swallow?');
GO