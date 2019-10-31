#Coin Mining
Objective: Find a 32-bit nonce (Integer.MIN_VALUE to Integer.MAX_VALUE) that has a hash less than a target

###Pseudo-code
```
do {
     Nonce++;
     hash = SHA256(SHA256(BlockInfo, Nonce));
} while (hash < target)
```


###Example output
```
Initial Block Hash:  0aca36d7d8e3bd46e6bab5bf3a47230e91e100ccd241c169e9d375f5b2a28f82
Initial Target Hash: 0000092a6893b712892a41e8438e3ff2242a68747105de0395826f60b38d88dc
Performing Proof-of-Work...wait...
Resulting Hash: 000006cf83ec42b3c0cba07e2c2b7bf4a40e3af16041fcc8e2651eb686d5f97d
Nonce:-2145652355
ElapsedTime (CurrentBlockID:1): 10.533094205 seconds
New Block Hash:  27af6a66fb3a14ef9582324c9a75ef7d668d0e3fedd2efb90d1d426934ade6f9
New Target Hash: 000004953449db89449520f421c71ff91215343a3882ef01cac137b059c6c46e

Performing Proof-of-Work...wait...
Resulting Hash: 000000b5b10d5364aac512a51b5df9718327d03c82bfd9d8ddfc7b9b78c51dbf
Nonce:-2146761970
ElapsedTime (CurrentBlockID:2): 3.495552888 seconds
New Block Hash:  dba400f156ecc994f9373b295ed167f2f6a531d56a466b2537e1a408b65dea61
New Target Hash: 0000024a9a24edc4a24a907a10e38ffc890a9a1d1c417780e5609bd82ce36237

Performing Proof-of-Work...wait...
Resulting Hash: 00000212061d0024c0d5ab610a8d01fbab94eef903d3d45a8e02696f7057a49b
Nonce:-2142197294
ElapsedTime (CurrentBlockID:3): 25.956508955 seconds
New Block Hash:  5984d858a67a3634c8c8c1548cfbf89dad1eccb7b26759dd8b762b2955519f28
New Target Hash: 000001254d1276e25125483d0871c7fe44854d0e8e20bbc072b04dec1671b11b

Performing Proof-of-Work...wait...
Resulting Hash: 00000043de3d9235b7b95a6d09f27e2943d5e037ca24bbb954e1515ed1236d48
Nonce:-2139171124
ElapsedTime (CurrentBlockID:4): 39.877384512 seconds
New Block Hash:  8fd2fb54fbd1d6d81e74228558f78b21109782c7b221656cd3456839202b91cc
New Target Hash: 0000024a9a24edc4a24a907a10e38ffc890a9a1d1c417780e5609bd82ce36236

Performing Proof-of-Work...wait...
Resulting Hash: 00000133e1af91ac2acb849fb0dd433eb14d168c86b92cc14a08da30996a39ca
Nonce:-2135987362
ElapsedTime (CurrentBlockID:5): 55.837174064 seconds
New Block Hash:  17e6b7997eddee39a0ac3380875422924431a513c0053afe9b1b8a1e6ea548ac
New Target Hash: 000004953449db89449520f421c71ff91215343a3882ef01cac137b059c6c46c

Performing Proof-of-Work...wait...
Resulting Hash: 000002136ff36dbc34969ef8ef17cc335a2b9ea5945da58a961fc6ad6338434b
Nonce:-2143511728
ElapsedTime (CurrentBlockID:6): 18.465659186 seconds
New Block Hash:  64e5ba4e48f951c17b399c13f087414f32680cce8711b3c788a93576e23f6b07
New Target Hash: 0000024a9a24edc4a24a907a10e38ffc890a9a1d1c417780e5609bd82ce36236

Performing Proof-of-Work...wait...
Resulting Hash: 000002087d5a5847bfd1fd9edcfe70487c1ae93b313424868a5de38eb96e4b02
Nonce:-2143813451
ElapsedTime (CurrentBlockID:7): 17.818596503 seconds
New Block Hash:  406883951a337db2f91995ee8772a4c27d6852622e4da935fdd9523860a54b3a
New Target Hash: 000001254d1276e25125483d0871c7fe44854d0e8e20bbc072b04dec1671b11b

Performing Proof-of-Work...wait...
Resulting Hash: 000000f42954e63fbf57d1169f3339d6e6631c2e97f33723c90c72a82d9c256b
Nonce:-2132897564
ElapsedTime (CurrentBlockID:8): 73.082979662 seconds
New Block Hash:  034ca43351582c995b5e9eeb62c5712f7166db5f8f0a239af704808809a5a25a
New Target Hash: 0000024a9a24edc4a24a907a10e38ffc890a9a1d1c417780e5609bd82ce36236

Performing Proof-of-Work...wait...
Resulting Hash: 000000abd379b7648c797e51c5a82e9f251ebbeb7309c9e7bc0835cb54999a56
Nonce:-2116569692
ElapsedTime (CurrentBlockID:9): 143.558355757 seconds
New Block Hash:  4f71f8b27e48bd94bd720128a026346265f93cce0573d4d0e5a300897872c97f
New Target Hash: 000004953449db89449520f421c71ff91215343a3882ef01cac137b059c6c46c
```