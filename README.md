# How To Run the Application
1.	Clone from GitHub (https://github.com/ishanul/StockMarketPrediction) or copy the StockMarketPrediction folder into your machine.


## Prerequisites
1.	JDK/ JRE 17
2.	Node JS v20.15.0
3.	Maven (Optional)


## Run The Backend
1.	Open the command line.
2.	Move the <Project Folder>/Backend/target
3.	Run below command
   
`java -jar stock-market-prediction-0.0.1-SNAPSHOT.jar`

### Output should be like below.

```
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.3.1)

[main] INFO com.ishan.liyanage.stock_market_prediction.StockMarketPredictionApplication - Starting StockMarketPredictionApplication using Java 17.0.11 with PID 23060 (C:\Users\Ishan Liyanage\OneDrive\Documents\MSC\Final_Project\StockMarketPrediction\Backend\target\classes started by Ishan Liyanage in C:\Users\Ishan Liyanage\OneDrive\Documents\MSC\Final_Project\StockMarketPrediction\Backend)
....
[main] INFO com.ishan.liyanage.stock_market_prediction.StockMarketPredictionApplication - Started StockMarketPredictionApplication in 1.419 seconds (process running for 1.719)
```

-------------------------------------


## Run The Web UI Client

1.	Open the command line.
2.	Move the <Project Folder>/Backend/target
3.	Run below command
   
`npm install`

### Output should be like below (It will take some time.)

```
up to date, audited 1502 packages in 2s

269 packages are looking for funding
  run `npm fund` for details

3 moderate severity vulnerabilities

To address all issues (including breaking changes), run:
  npm audit fix --force

Run `npm audit` for details.
```
4.	Run the below command
   
`npm start`

### Output should be like below

```
$ npm start

> black-dashboard-react@1.2.2 start
> react-scripts start

Compiled successfully!

You can now view black-dashboard-react in the browser.

  Local:            http://localhost:3000/@ishanul
  On Your Network:  http://192.168.1.35:3000/@ishanul

Note that the development build is not optimized.
To create a production build, use npm run build.

webpack compiled successfully
```
