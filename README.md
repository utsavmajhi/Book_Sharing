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

[![Build Status](http://img.shields.io/travis/badges/badgerbadgerbadger.svg?style=flat-square)](https://travis-ci.org/badges/badgerbadgerbadger) [![Dependency Status](http://img.shields.io/gemnasium/badges/badgerbadgerbadger.svg?style=flat-square)](https://gemnasium.com/badges/badgerbadgerbadger) [![Coverage Status](http://img.shields.io/coveralls/badges/badgerbadgerbadger.svg?style=flat-square)](https://coveralls.io/r/badges/badgerbadgerbadger) [![Code Climate](http://img.shields.io/codeclimate/github/badges/badgerbadgerbadger.svg?style=flat-square)](https://github.com/utsavmajhi/Book_Sharing/issues) [![Github Issues](http://githubbadges.herokuapp.com/badges/badgerbadgerbadger/issues.svg?style=flat-square)](https://github.com/utsavmajhi/Book_Sharing/issues) [![Pending Pull-Requests](http://githubbadges.herokuapp.com/badges/badgerbadgerbadger/pulls.svg?style=flat-square)](https://github.com/badges/badgerbadgerbadger/pulls)[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org) [![Badges](http://img.shields.io/:badges-9/9-ff6799.svg?style=flat-square)](https://github.com/badges/badgerbadgerbadger)


[![INSERT YOUR GRAPHIC HERE](http://i.imgur.com/dt8AUb6.png)]()

- Most people will glance at your `README`, *maybe* star it, and leave
- Ergo, people should understand instantly what your project is about based on your repo


## Table of Contents (Optional)

> If your `README` has a lot of info, section headers might be nice.

- [Installation](#installation)
- [Features](#features)
- [Contributing](#contributing)
- [Team](#team)
- [FAQ](#faq)
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

## Usage (Optional)

- Going into more detail on code and technologies used
- I utilized this nifty <a href="https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet" target="_blank">Markdown Cheatsheet</a> for this sample `README`.

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

## Team

> Or Contributors/People

| <a href="http://fvcproductions.com" target="_blank">**FVCproductions**</a> | <a href="http://fvcproductions.com" target="_blank">**FVCproductions**</a> | <a href="http://fvcproductions.com" target="_blank">**FVCproductions**</a> |
| :---: |:---:| :---:|
| [![FVCproductions](https://avatars1.githubusercontent.com/u/4284691?v=3&s=200)](http://fvcproductions.com)    | [![FVCproductions](https://avatars1.githubusercontent.com/u/4284691?v=3&s=200)](http://fvcproductions.com) | [![FVCproductions](https://avatars1.githubusercontent.com/u/4284691?v=3&s=200)](http://fvcproductions.com)  |
| <a href="http://github.com/fvcproductions" target="_blank">`github.com/fvcproductions`</a> | <a href="http://github.com/fvcproductions" target="_blank">`github.com/fvcproductions`</a> | <a href="http://github.com/fvcproductions" target="_blank">`github.com/fvcproductions`</a> |

- You can just grab their GitHub profile image URL
- You should probably resize their picture using `?s=200` at the end of the image URL.

---

## FAQ

- **How do I do *specifically* so and so?**
    - No problem! Just do this.

---

## Support

Reach out to me at one of the following places!

- Linkedin at<a href="https://www.linkedin.com/in/utsav-majhi/" target="_blank"></a>
- Facebook at <a href="https://www.facebook.com/utsav.majhi.3" target="_blank"></a>


---

## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
- Copyright 2015 © <a href="http://fvcproductions.com" target="_blank">FVCproductions</a>.
