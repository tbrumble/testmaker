# testmaker
Generate test from template

Test template contains in folder: template/
Warning! Now can create one kind of test - for /user/get-all rest 

run gradle task - 
gradle accessTestTask --restPath="user/get-all" --switcherName="dbo_access_userverify" --testName="Example1GreatTest" --testPackageName="not ready"

where:
1 restPath - path to your rest methods 
2 switcherName - kind of access switcher 
3 testName - your wonderfull test name
4 testPackageName - in what package do you want to save your generated test (not work now)

Where you can find switcher list - in src/main/resources/data.sql
Application use PostgresDb - change it in application.properties

When test is generated - move it to test folder and run.

Custom gradle task in the buildSrc/src/main/java folder
