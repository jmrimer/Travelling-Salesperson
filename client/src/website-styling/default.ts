import styled from 'styled-components';

export const theme = {
  background: 'linear-gradient(360deg,#1E222A 0%,#39414E 100%)',
  fontFamily: 'Righteous',
  color: {
    fontWhite: '#e6e5e5',
    foreground: '#c4c4c4',
    lavender: '#443D7C',
    wedgewood: '#50819B',
    midnight: '#09132d',
    plum: '#230a27',
    maroon5: '#6D2234',
    flare: '#C83A17'
  }
};

export const Button = styled.button`
  display: inline-block;
  font-family: Righteous, cursive;
  font-size: 24px;
  flex: 1;
  background: ${(props) => props.theme.color.wedgewood};
  border: 1px solid ${(props) => props.theme.color.foreground};
  border-radius: 8px;
  color: white;
  padding: 16px;
  cursor: pointer;
  
  :hover {
    background: ${(props) => props.theme.color.lavender};
  }
`;

export const Input = styled.input`
  font-family: 'Roboto', sans-serif;
  font-size: 16px;
  text-align: right;
  background: ${(props) => props.theme.color.foreground};
  border: solid 2px ${(props) => props.theme.color.wedgewood};
`;

export const Heading = styled.h1`
  width: 100%;
  font-family: Righteous, cursive;
  font-size: 36px;
  color: ${(props) => props.theme.color.fontWhite};
`;