<img src="https://github.com/utsavmajhi/Book_Sharing/blob/master/app/src/main/res/drawable-v24/bookp1.png" width="200" height="200" title="BookSharing" alt="Platform"></a>


# Book_Sharing
## Overview
> As most of us know that hard-copy books are very costly and 
in college,there is always some student who has a certain book that is needed by another student.
In this project,we are trying to build a decentralized platform for students to share their books
## Features
1. owner of book can track his/her book in realtime.
2. Books will be secured using the fabric network.
3. The owner can remove his/her book from the platform.

[![Build Status](http://img.shields.io/travis/badges/badgerbadgerbadger.svg?style=flat-square)](https://travis-ci.org/badges/badgerbadgerbadger) [![Dependency Status](http://img.shields.io/gemnasium/badges/badgerbadgerbadger.svg?style=flat-square)](https://gemnasium.com/badges/badgerbadgerbadger)[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)  [![Coverage Status](http://img.shields.io/coveralls/badges/badgerbadgerbadger.svg?style=flat-square)](https://coveralls.io/r/badges/badgerbadgerbadger)(https://github.com/badges/badgerbadgerbadger/pulls)[![Badges](http://img.shields.io/:badges-9/9-ff6799.svg?style=flat-square)](https://github.com/badges/badgerbadgerbadger)


|<img src="https://github.com/utsavmajhi/Book_Sharing/blob/master/pics/3.jpg" width="220" height="400" title="LoginPage" alt="LoginPage"></a>|<img src="https://github.com/utsavmajhi/Book_Sharing/blob/master/pics/1.jpg" width="220" height="400" title="RegisterPage" alt="RegisterPage"></a>|<img src="https://github.com/utsavmajhi/Book_Sharing/blob/master/pics/7.jpg" width="220" height="400" title="BookdetailsPage" alt="BookDetails"></a>|<img src="https://github.com/utsavmajhi/Book_Sharing/blob/master/pics/8.jpg" width="220" height="400" title="ProfilePage" alt="Profile"></a>|<img src="https://github.com/utsavmajhi/Book_Sharing/blob/master/pics/5.jpg" width="220" height="400" title="MyBooks" alt="MyBooks"></a>
-
-
## Table of Contents

- [Installation](#installation)
- [Features](#features)
- [Contributing](#contributing)
- [Team](#team)
- [Support](#support)
- [License](#license)


---

## Installation
### Clone

- Clone this repo to your local machine using `https://github.com/utsavmajhi/Book_Sharing`

# Start The network
## Generate the channel artifacts and crypto files (Optional)
        cryptogen generate --config=./crypto-config.yaml 
        configtxgen -profile Genesis -outputBlock channel-artifacts/genesis.block -channelID genesis 
        configtxgen -outputCreateChannelTx channel-artifacts/channel.tx -profile BookChannel -channelID bookchannel 
        configtxgen -outputAnchorPeersUpdate channel-artifacts/HostAnchorUPdate.tx -profile BookChannel -channelID bookchannel -asOrg Host
## Start Docker Containers and setup the peers
1. Change the *-cert.pem to ``cert.pem`` in ca folder of peerOrganizations , and private key to ```PRIVATE_KEY```
2.      cd blockchain/network/dockerblockchain
        docker-compose up -d
3.      docker exec -it cli bash
        cd /channel-artifacts
        peer channel create -f channel.tx -o orderer:7050 -c bookchannel
        peer channel join -b bookchannel.block
        peer channel update -f HostAnchorUPdate.tx -o orderer:7050 -c bookchannel
        peer chaincode install -n book -v 0 -p chaincode
        peer chaincode instantiate -n book -v 0 -C bookchannel -c '{"args":[]}'

## Fire up the AIPs
1. cd blockchain/app/
2.      node ./client/enrollAdmin.js
        node ./client/clientRegister.js
3.      nodemon ./testapi/api.js

### Setup

- If you want more syntax highlighting, format your code like this:

> update and install this package first

```shell
$ brew update
$ brew install fvcproductions
```

> now install npm and bower packages

```shell
$ npm install
$ bower install
```

- For all the possible languages that support syntax highlithing on GitHub (which is basically all of them), refer <a href="https://github.com/github/linguist/blob/master/lib/linguist/languages.yml" target="_blank">here</a>.

---

## Contributing

> To get started...

### Step 1

- **Option 1**
    - 🍴 Fork this repo!

- **Option 2**
    - 👯 Clone this repo to your local machine using `https://github.com/utsavmajhi/Book_Sharing`

### Step 2

- **HACK AWAY!** 🔨🔨🔨

### Step 3

- 🔃 Create a new pull request using <a href="https://github.com/utsavmajhi/Book_Sharing/compare/" target="_blank">`https://github.com/utsavmajhi/Book_Sharing/compare/`</a>.

---

## Contributors

| <a href="https://github.com/Zzocker" target="_blank">**Pritam Singh**</a> | <a href="https://github.com/utsavmajhi" target="_blank">**Utsav Majhi**</a> | 
| :---: |:---:|
| [![Pritam Singh](https://avatars1.githubusercontent.com/u/43764373?s=200&u=6a3ef280e24c5ffe3b5e108338e028ca4e0745e4&v=4)](https://github.com/Zzocker)    | [![Utsav Majhi](https://avatars1.githubusercontent.com/u/43748319?s=200&u=ac58aeeb7a30b09265baac08d6ef78aab4887aae&v=4)](https://github.com/utsavmajhi) |

---


## Support

Reach out to me at one of the following places!

- Linkedin at<a href="https://www.linkedin.com/in/utsav-majhi/" target="_blank">`Link`</a>
- Facebook at <a href="https://www.facebook.com/utsav.majhi.3" target="_blank">`Link`</a>


---

## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
Copyright (c) 2020 Pritam Singh and Utsav Majhi
```MIT License
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.```

