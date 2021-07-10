import './App.css';
import { Inicio } from './pages/Inicio';
import { Principal } from './pages/Principal';

import { BrowserRouter as Router, Switch, Route} from 'react-router-dom'; 

import { firebaseConfig } from "../src/components/FireBaseConfig";
import firebase from 'firebase/app';
import 'firebase/auth';

firebase.initializeApp(firebaseConfig);

export const url = "localhost:8080/api";


function App() {
  return (
    <>
      <Router>
          <Switch>
            <Route path='/' exact component={Inicio}/>
            
          </Switch>
      </Router>

    </>
  );
}

export default App;
