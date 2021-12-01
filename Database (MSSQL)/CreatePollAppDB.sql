Go
CREATE DATABASE PollApp;
Go
USE PollApp;  
CREATE TABLE dbo.AppUser
 (  
   UserID int identity(1,1) NOT NULL Primary Key,   
   Username nvarchar(50) NOT NULL UNIQUE,
   PasswordHash char(64) NOT NULL,
   UserRole varchar(5) NOT NULL CHECK (UserRole IN('admin', 'user'))
);  

CREATE TABLE dbo.Question
 (  
   QuestionID int identity(1,1) NOT NULL Primary Key,
   Question nvarchar(MAX) NOT NULL,
);
 
CREATE TABLE dbo.Answer
 (  
   AnswerID int identity(1,1) NOT NULL,
   QuestionID int NOT NULL,
   Answer nvarchar(MAX) NOT NULL,

   primary key (AnswerID),
   Foreign Key (QuestionID) REFERENCES Question(QuestionID)
);  

CREATE TABLE dbo.Vote
 (  
   UserID int NOT NULL,
   AnswerID int NOT NULL,

   Primary Key (UserID, AnswerID),
   Foreign Key (UserID) REFERENCES AppUser(UserID),
   Foreign Key (AnswerID) REFERENCES Answer(AnswerID)
);