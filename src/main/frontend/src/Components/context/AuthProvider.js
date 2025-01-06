import {createContext, ustState} from 'react';

export const AuthContext = createContext();


function AuthProvider({children}) {
    const [auth, setAuth] = ustState(localStorage.getItem("id"));

    const value = {auth, setAuth};

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>

    );

}

export default AuthProvider;