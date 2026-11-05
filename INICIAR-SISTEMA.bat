@echo off
setlocal
chcp 65001 >nul
title Escola de Magia - API
cd /d "%~dp0"
if not exist ".env" (
  echo ERRO: copie .env.example para .env e informe os dados do PostgreSQL.
  pause
  exit /b 1
)
for /f "usebackq tokens=1,* delims==" %%A in (".env") do (
  if not "%%A"=="" if not "%%A:~0,1"=="#" set "%%A=%%B"
)
if exist "C:\Program Files\Java\jdk-23\bin\java.exe" set "JAVA_HOME=C:\Program Files\Java\jdk-23"
if exist "C:\Program Files\Java\jdk-21\bin\java.exe" set "JAVA_HOME=C:\Program Files\Java\jdk-21"
if defined JAVA_HOME set "PATH=%JAVA_HOME%\bin;%PATH%"
echo ESCOLA DE MAGIA - CATALOGO DE CURSOS
echo O PostgreSQL local precisa estar iniciado e configurado no arquivo .env.
echo Mantenha esta janela aberta. Para encerrar, pressione Ctrl+C.
echo.
call .\mvnw.cmd spring-boot:run
pause
