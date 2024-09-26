import React from 'react';
import {Link} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "../components/Header";



export default function MainPage() {

    const { UserID } = useParams();
    const [User, setUser] = useState({});

    useEffect(() => {
        if (UserID) {
            fetch('http://localhost:8080/getGuideByID/' + UserID)
                .then(response => response.json())
                .then(data => setUser(data))
                .catch(error => console.error(error));
        }
        else {
            setUser(null)
        }
    }, [UserID]);

    return(
        <>
            <Header user={User} />
            <></>
        </>
    )
}