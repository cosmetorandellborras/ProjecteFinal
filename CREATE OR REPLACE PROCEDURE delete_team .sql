CREATE OR REPLACE PROCEDURE delete_team (team VARCHAR2)
IS
del_team club.TEAM_NAME%TYPE;
row club%ROWTYPE;
cursor c1 
IS SELECT TEAM_NAME FROM CLUB FOR UPDATE OF TEAM_NAME;
BEGIN
select TEAM_NAME into del_team from club;
open c1;
    LOOP
    FETCH c1 INTO del_team;
    EXIT WHEN c1%notfound;
        IF del_team = team THEN
            delete_statics(del_team);
            delete_players(del_team);
            delete_calendar(del_team);
            DELETE FROM CLUB WHERE CURRENT OF c1;
        END IF;
    END LOOP;
close c1;
EXCEPTION 
WHEN no_data_found THEN
DBMS_OUTPUT.PUT_LINE("No se ha encontrado ningun equipo con el nombre introducido");
WHEN too_many_rows THEN
DBMS_OUTPUT.PUT_LINE("Se ha encontrado mas de un equipo con el nombre introducido");
END;
/

CREATE OR REPLACE PROCEDURE delete_statics(team VARCHAR2)
IS
pname players.PLAYER_NAME%TYPE;
tname players.TEAM_NAME%TYPE;
pnamestat statics.PLAYER_NAME%TYPE;
cursor c1
IS SELECT player_name, team_name FROM PLAYERS;
cursor c2
IS SELECT player_name FROM STATICS FOR UPDATE OF PLAYER_NAME;
BEGIN
open c1;
    LOOP
    FETCH c1 INTO pname,tname;
    EXIT WHEN c1%notfound;
        IF tname=team THEN
            open c2;
            LOOP
            FETCH c2 into pnamestat;
            EXIT WHEN c2%notfound;
                IF pname=pnamestat THEN
                DELETE FROM STATICS WHERE CURRENT OF c2;
                END IF;
            END LOOP;
            close c2;
        END IF;
    END LOOP;
close c1;
END;
/
CREATE OR REPLACE PROCEDURE delete_players(team VARCHAR2)
IS
tname players.TEAM_NAME%TYPE;
cursor c1
IS SELECT TEAM_NAME from players for update of PLAYER_NAME;
BEGIN
open c1;
    LOOP
    FETCH c1 into tname;
    EXIT WHEN c1%notfound;
        IF tname = team THEN
        DELETE FROM PLAYERS WHERE CURRENT OF c1;
        END IF;
    END LOOP;
close c1;
END;
/
CREATE OR REPLACE PROCEDURE delete_calendar(team VARCHAR2)
IS
locteam CALENDAR.LOCAL_TEAM%TYPE;
forteam CALENDAR.FOREIGN_TEAM%TYPE;
cursor c1
IS SELECT LOCAL_TEAM,FOREIGN_TEAM FROM CALENDAR FOR UPDATE OF LOCAL_TEAM;
BEGIN
open c1;
    LOOP
    FETCH c1 INTO locteam,forteam;
    EXIT WHEN c1%notfound;
        IF locteam = team OR forteam = team THEN
        DELETE FROM CALENDAR WHERE CURRENT OF c1;
        END IF;
    END LOOP;
close c1;
END;
/

select PLAYER_NAME, TEAM_NAME from PLAYERS;
select * from STATICS;