import Login from './pages/Login';
import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";

export const url = 'localhost:8080/api';


export default function Router(){
    return(
        <BrowserRouter>
            <Switch>
                <Router exact path='/' component={Login}/>

            </Switch>
        </BrowserRouter>
    );
}