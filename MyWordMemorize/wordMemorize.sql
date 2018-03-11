create table wordMemorize_Member (
                memberNumber number primary key,
                memberID varchar2(15) unique not null,
                password varchar2(20) not null,
                memberName varchar2(15) not null,
                registeredDate date default sysdate,
                memberLevel number default 1,
                blinkGameCount number default 0
);
create sequence wordMemorize_Member_seq;

drop table wordMemorize_Member;
drop sequence wordMemorize_Member_seq;

create table wordMemorize_Word (
                wordNumber number primary key,
                word varchar2(40) not null,
                yomigana varchar2(45) not null,
                meaning varchar2(80) not null,
                wordLevels varchar2(10) not null,
                linkAddress varchar2(60) not null
);
create sequence wordMemorize_Word_seq;

drop table wordMemorize_Word;
drop sequence wordMemorize_Word_seq;

create table wordMemorize_CustomWord (
                wordNumber number primary key,
                word varchar2(40) not null,
                yomigana varchar2(45) not null,
                meaning varchar2(80) not null,
                wordLevels varchar2(10) not null,
                linkAddress varchar2(60) not null,
                addedMemberID varchar2(15) not null
);
create sequence wordMemorize_CustomWord_seq;

drop table wordMemorize_CustomWord;
drop sequence wordMemorize_Word_seq;

create table wordMemorize_BlinkGame (
                blinkGameNumber number primary key,
                memberID varchar2(15) not null,
                highScore number default 0,
                maxCombo number default 0,
                playCount number default 0,
                constraint memberID_FK foreign key (memberID)
                references wordMemorize_Member (memberID) on delete cascade
);
create sequence wordMemorize_BlinkGame_seq;

drop table wordMemorize_BlinkGame;
drop sequence wordMemorize_BlinkGame_seq;

create table wordMemorize_BlinkGameInfo (
                blinkGameInfoNumber number primary key,
                incollectBGameWord varchar2(40),
                memberID varchar2(15) not null,
                incollectingCount number default 1,
                constraint BGImemberID_FK foreign key (memberID)
                references wordMemorize_Member (memberID) on delete cascade
);
create sequence wordMemorize_BlinkGameInfo_seq;

drop table wordMemorize_BlinkGameInfo;
drop sequence wordMemorize_BlinkGameInfo_seq;