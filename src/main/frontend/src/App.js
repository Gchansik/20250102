import logo from './logo.svg';
import './App.css';
import {BrowserRouter} from 'react-router-dom';

import Header from './Components/app/Header';
import Main from './Components/app/Main';
import Footer from './Components/app/Footer';


//css
import "./css/style.css";
import "./css/main.css";


import HttpHeaderProvider from './Components/context/HttpHeaderProvider';
import AuthProvider from './Components/context/AuthProvider';



function App() {
  return (
    <div>
        <BrowserRouter>
            <AuthProvider>
                <HttpHeaderProvider>
                    <Header />
                    <Main />
                    <Footer />
                </HttpHeaderProvider>
            </AuthProvider>
        </BrowserRouter>
    </div>
  );
}

export default App;
