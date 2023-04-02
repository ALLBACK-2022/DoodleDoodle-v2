import axios from '../utils/axios';

const headers = {
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Methods': 'GET,POST,PUT,DELETE,OPTIONS',
  'Access-Control-Allow-Headers': 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With',
  'Content-type': 'application/json; charset=UTF-8',
};

export const createGame = async userNum => {
  const req = { 'user-num': userNum };
  const response = await axios.post('/games', req, headers);
  return response;
};
