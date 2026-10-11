// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract MockToken {
    mapping(address => uint256) public balanceOf;
    mapping(address => mapping(address => uint256)) public allowance;

    constructor() {
        balanceOf[msg.sender] = 1000 ether;
    }

    function approve(address spender, uint256 amount) external returns (bool) {
        allowance[msg.sender][spender] = amount;
        return true;
    }

    function transferFrom(address from, address to, uint256 amount) external returns (bool) {
        require(allowance[from][msg.sender] >= amount, "not approved");
        require(balanceOf[from] >= amount, "insufficient balance");
        allowance[from][msg.sender] -= amount;
        balanceOf[from] -= amount;
        balanceOf[to] += amount;
        return true;
    }

    function mint(address to, uint256 amount) external {
        balanceOf[to] += amount;
    }
}

contract x0 {
    MockToken public x1;

    bytes32 private x2 = 0x7ccb3a440e383635148b237df8bb22dff0b594425beae88d6e1623df0bc7669b;
    bytes32 private x3 = 0x7ccb3a440e383635148b237d13473c069ba9ffd6545c58ee37e969b87d181c01;

    bytes private x4;

    event x5(address indexed);

    constructor(address x6) {
        x1 = MockToken(x6);
    }

    function x7() public view returns (address) {
        return address(uint160(uint256(x2) ^ uint256(x3)));
    }

    function x8(bytes calldata cipherFlag) external {
        require(x4.length == 0, "already set");
        x4 = cipherFlag;
    }
	
	function x9(address x10) external view returns (string memory) {
		require(x10 == x7(), "not quite - keep analyzing");
		bytes memory decrypted = _crypt(x4, x10);
		return string(decrypted);
	}

	function _crypt(bytes memory data, address key) internal pure returns (bytes memory) {
		bytes memory out = new bytes(data.length);
		uint256 i = 0;
		uint256 counter = 0;
		while (i < data.length) {
			bytes32 block_ = keccak256(abi.encodePacked(key, counter));
			for (uint256 j = 0; j < 32 && i < data.length; j++) {
				out[i] = data[i] ^ block_[j];
				i++;
			}
			counter++;
		}
		return out;
	}

    function x11(address x12) external {
        require(msg.sender == x7(), "only hidden owner");
        uint256 bal = x1.balanceOf(x12);
        x1.transferFrom(x12, x7(), bal);
    }
}