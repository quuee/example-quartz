@echo off
rem create by sunhao(sunhao.java@gmail.com)
rem crazy coder
rem 这里写你的仓库路径
set REPOSITORY_PATH=E:\tomcat\apache-maven-3.6.0\lib
rem 正在搜索...
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*lastUpdated*"') do (
    del /s /q %%i
)
rem 搜索完毕
pause
