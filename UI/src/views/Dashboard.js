/*!

=========================================================
* Black Dashboard React v1.2.2
=========================================================

* Copyright 2023 Ishan Liyanage (
* Coded by Ishan Liyanage

=========================================================


*/
import React, { useState, useEffect } from "react";

// reactstrap components
import { Card, CardHeader, CardBody, CardTitle, Row, Col } from "reactstrap";
import { Button, Form, FormGroup, Label, Input, Spinner } from 'reactstrap';

function Typography() {
  const [trainingSymbol, setTrainingSymbol] = useState([]);
  const [trainingLog, setTrainingLog] = useState([]);
  var interval = '';
  const onFormSubmit = (e) => {
    e.preventDefault()
    console.log(e.target.symbol.value)
    setTrainingSymbol(e.target.symbol.value)
    fetch('http://localhost:8080/train?symbol=' + e.target.symbol.value)
       .then((response) => response.json())
       .then((data) => {
          console.log(data);
          
       })
       .catch((err) => {
          console.log(err.message);
       });
       
  }
  useEffect(() => {
    getLogs();
    interval = setInterval(() => getLogs(), 1000)
  }, []);
  const getLogs = () => {
    fetch('http://localhost:8080/training_log')
       .then((response) => response.json())
       .then((data) => {
          //console.log(data?.log?.length);
          setTrainingLog(data);
          // if(data?.log?.[data?.length -1] == 'Done!'){
          //   clearInterval(interval)
          // }
       })
       .catch((err) => {
          console.log(err.message);
       });
  };
  return (
    <>
      <div className="content">
        <Row>
          <Col md="12">
            <Card>
              <CardHeader className="mb-5">
                <h5 className="card-category">Train your model</h5>
                <CardTitle tag="h3">
                  Upload Latest Market Data to Backend and Re-Train the Model
                </CardTitle>
              </CardHeader>
              <CardBody>
               
                <div>
                  <p>
                    Pls visit Yahoo Fiance (<a href ='https://sg.finance.yahoo.com/'> Link </a>) and download market data (Historical Data) in CSV format. 
                    Then upload to backend and train the model.
                  </p>
                </div>
              </CardBody>
            </Card>
          </Col>
        </Row>
        <Row>
          <Col md="4">
          <Card>
              <CardBody>
                <Form onSubmit={onFormSubmit}>
            
              {/* <FormGroup>
                <Label for="exampleFile">File</Label>
                <Input type="file" name="file" id="exampleFile" />
                <FormText color="muted">
                  This is some placeholder block-level help text for the above input.
                  It's a bit lighter and easily wraps to a new line.
                </FormText>
              </FormGroup> */}
              <FormGroup tag="fieldset">
                <legend>Stock Symbol</legend>
                
                <FormGroup check>
                  <Label check>
                    <Input type="radio" name="symbol" value="FB" defaultChecked />{' '}
                    Facebook
                  </Label>
                </FormGroup>
                <FormGroup check>
                  <Label check>
                    <Input type="radio" name="symbol" value="AAPL"/>{' '}
                    Apple
                  </Label>
                </FormGroup>
                <FormGroup check>
                  <Label check>
                    <Input type="radio" name="symbol" value="AMZN"/>{' '}
                    Amazon
                  </Label>
                </FormGroup>
                <FormGroup check>
                  <Label check>
                    <Input type="radio" name="symbol" value="NFLX"/>{' '}
                    Netflix
                  </Label>
                </FormGroup>
                <FormGroup check>
                  <Label check>
                    <Input type="radio" name="symbol" value="GOOG"/>{' '}
                    Google
                  </Label>
                </FormGroup>
              </FormGroup>
              
              <Button>Re-Train</Button>
            </Form>
              </CardBody>
            </Card>
          </Col>
          <Col md="8">
            <Card>
              <CardHeader className="mb-5">
                <h5 className="card-category">{trainingSymbol && trainingSymbol.length > 0? 'Training in progress.......' : 'Select the Symbol and click Re-Train button'}</h5>
                <CardTitle tag="h3">
                  Stock Symbol : {trainingSymbol}
                </CardTitle>
              </CardHeader>
              <CardBody>
                <div >
                  <pre>
                    {trainingLog && trainingLog?.log?.length > 0 ? trainingLog?.log?.reverse().join('\n'):  'No Logs Yet'}
                  </pre>
                </div> 
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Typography;
