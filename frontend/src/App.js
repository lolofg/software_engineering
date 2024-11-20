import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import MainPage from "./pages/MainPage";
import MainPageUser from "./pages/MainPageUser";
import MainPageAdmin from "./pages/MainPageAdmin";

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<MainPage/>}/>
                <Route path="/Login" element={<Login/>}/>

                <Route path="/User/:UserID" element={<MainPageUser/>}/>

                <Route path="/User/:UserID" element={<MainPage/>}/>

                <Route path="/Admin" element={<MainPageAdmin/>}/>
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;