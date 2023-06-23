# testLoginRegister
/api/auth/register Rejestracja (POST) Dostępne dla wszystkich
/api/auth/authenticate Logowanie (POST) Dostępne dla wszystkich

/api/project Pobierz wszystkie projekty (GET) Tylko zalogowani
/api/project/{id} Pobierz konkretny projekt (GET) Tylko zalogowani
/api/project/admin Stwórz nowy projekt (POST) Tylko admin
/api/project/admin/{id} Usuń wybrany projekt (DELETE) Tylko admin
/api/project/{id} Update wybranego projektu (PUT) Tylko zalogowani
/api/project/admin/{projectId}/user/{userId} Dodaj użytkownika do projektu (POST) Tylko admin
/api/project/{projectId}/user Pobierz użytkowników danego projektu (GET)

/api/project/{projectId}/task Pobierz zadania danego projektu (GET) Tylko zalogowani
/api/project/{projectId}/task Dodaj zadanie do danego projektu (GET) Tylko zalogowani
/api/project/{projectId}/task/{taskId} Usuń dane zadanie danego projektu (DELETE) Tylko zalogowani
